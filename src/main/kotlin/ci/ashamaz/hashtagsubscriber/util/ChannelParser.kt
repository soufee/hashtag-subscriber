package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.Message
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import ci.ashamaz.hashtagsubscriber.service.MessageService
import ci.ashamaz.hashtagsubscriber.util.extention.setLinks
import ci.ashamaz.hashtagsubscriber.util.extention.shorten
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.safety.Whitelist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.io.IOException
import kotlin.IllegalArgumentException
import kotlin.streams.toList

@Component
@PropertySource("classpath:constants.properties")
class ChannelParser {
    @Value("\${parse.default.url}")
    private val uri2: String = ""

    @Value("\${parse.default.classifier}")
    private val classifier = ""

    @Value("\${parse.default.classifier_parent}")
    private val classifierParent = ""

    @Value("\${parse.default.photo_classifier}")
    private val photoClassifier = ""

    @Value("\${parse.default.video_classifier}")
    private val videoClassifier: String = ""

    private val logger: Logger = LoggerFactory.getLogger(ChannelParser::class.java)

    @Autowired
    val channelService: ChannelService? = null

    @Autowired
    val tagUtil: TagUtil? = null

    @Autowired
    val messageService: MessageService? = null

    fun getDocument(url: String = uri2): Document {
        if (url.isEmpty()) throw IllegalArgumentException("Передан некоректный url = $url")
        return Jsoup.connect(url).get()
    }

    @Throws(IOException::class)
    fun parse(doc: Document?) {
        if (doc == null) throw IllegalArgumentException("Предан некорректный документ")
        val title = doc.getElementsByTag("title").html().replace(" – Telegram", "")
        val elements = doc.getElementsByClass(classifierParent)
        val newUrls = mutableSetOf<String>()
        for (e in elements) {
            val txt = completeMessage(e, title)
            val key = e.attributes()["data-post"]
            val str = e.toString()// вот тут нужно анализировать на предмет наличия хэштэгов
            var channel = channelService?.getChannelByLink(getChannelUrl(e))
            if (channel != null) {
                val tags = tagUtil?.getTagsFromText(str)

                if (!tags.isNullOrEmpty()) {
                    val messId = key.split("/").last().toLong()
                    val message = Message(
                            messageId = messId,
                            title = title,
                            text = txt,
                            link = "http://t.me/$key",
                            channel = channel
                    )
                    message.setLinks()
                    message.tags = tags
                    messageService?.save(message)
                }
            }

            val linksFromMessages = extractUrls(str) as MutableSet
            newUrls.addAll(linksFromMessages)
        }

        logger.debug(newUrls.joinToString(" "))
        newUrls.forEach {
            var channel = channelService?.getChannelByLink(it)
            if (channel == null) {
                val channelName = getChannelName(it)
                channel = Channel(
                        channelName = channelName
                                ?: throw IllegalStateException("Не удалось создать канал по урлу $it"),
                        link = it
                )
                channel.setLinks()
                channelService?.saveOrUpdate(channel)
            }
        }
    }

    fun extractUrls(str: String): Set<String> {
        val links = str.split("<a href=", " ").filter { it.startsWith("\"https://t.me/") }
        val list: MutableList<String> = links.stream()
                .map { it.replace("\"", "").replace(">", "").replace("<.".toRegex(), "") }
                .toList().toMutableList()
        for (i in list.indices) {
            val arr = list[i].split("/")
            if (arr.size > 4) {
                val url = list[i].substring(0, list[i].lastIndexOf("/"))
                list[i] = url
            }

        }

        return HashSet<String>(list)
    }

    fun completeMessage(e: Element, title: String): String {
        val channelUrl = getChannelUrl(e)
        val pictures = e.getElementsByClass(photoClassifier)
        val messages = e.getElementsByClass(classifier)
        val videos = e.getElementsByClass(videoClassifier)
        val content = StringBuilder()
        content.append("""<b><a href="${channelUrl}">$title</a></b>""")

        if (messages.size > 0) {
            val value = e.getElementsByClass(classifier)[0]
            val builder = StringBuilder()
            val valueText = Jsoup.clean(value.toString(), Whitelist.basicWithImages()).replace("<br>", "")

            with(builder) {
                append("\n")
                append(valueText.shorten())
                append("\n")
            }
            content.append(builder)
        }
        if (pictures.size > 0) {
            pictures.forEach { p ->
                content.append("<b>[PHOTO]</b>")
            }
        }
        if (videos.size > 0) {
            videos.forEach { p ->
                content.append("<b>[VIDEO]</b>")
            }
        }

        val message = content.toString()
        return message
    }

    /**
     * Метод принимает Элемент и формирует урл канала, которому он принадлежит
     * */
    fun getChannelUrl(e: Element): String {
        val channelName = getChannelName(e)
        return "https://t.me/${channelName}"
    }

    /**
     * Метод принимает Элемент и извлекает из него наименование канала
     * */
    fun getChannelName(e: Element): String? {
        val key = e.getElementsByAttribute("data-post").attr("data-post")
        return key.split("/").first()
    }

    /**
     * Метод принимает урл канала и извлекает из него название канала
     * */
    fun getChannelName(url: String?): String? {
        if (url?.startsWith("https://t.me/") != true) {
            throw IllegalArgumentException("Переданная строка не может быть распарсена корректно")
        }
        val str = url.substring(13)
        val strElements = str.split("/")
        if ("s".equals(strElements[0])) return strElements[1]
        else return strElements[0]
    }


//    fun sendMsg(s: String?, url: String? = null) {
//        val message = SendMessage()
//        message.disableWebPagePreview()
//        message.enableMarkdown(false)
//        message.enableHtml(true)
//        message.chatId = "" + 87927916
//        message.text = s
//        if (url != null) {
//            val inlineKeyboardMarkup = InlineKeyboardMarkup()
//            val keyboardButtonsRow1 = makeRedirectButton(url)
//            inlineKeyboardMarkup.keyboard.add(keyboardButtonsRow1)
//            message.replyMarkup = inlineKeyboardMarkup
//        }
//
//
//        CommandFactory.messageQueue.add(message)
//    }

//    fun makeRedirectButton(url: String, name: String = "Читать в источнике"): List<InlineKeyboardButton> {
//        val inlineKeyboardButton = InlineKeyboardButton()
//        inlineKeyboardButton.text = name
//        inlineKeyboardButton.callbackData = "Button $name has been pressed"
//        inlineKeyboardButton.url = url
//        val keyboardButtonsRow1 = mutableListOf<InlineKeyboardButton>()
//        keyboardButtonsRow1.add(inlineKeyboardButton)
//        return keyboardButtonsRow1
//    }
}