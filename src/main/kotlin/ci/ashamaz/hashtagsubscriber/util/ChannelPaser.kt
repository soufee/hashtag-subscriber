package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.bot.processor.CommandFactory
import ci.ashamaz.hashtagsubscriber.bot.processor.intrfc.ProcessPost
import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.Message
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import ci.ashamaz.hashtagsubscriber.service.MessageService
import ci.ashamaz.hashtagsubscriber.util.extention.setLinks
import ci.ashamaz.hashtagsubscriber.util.extention.shorten
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import com.google.gson.Gson
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.safety.Whitelist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import kotlin.streams.toList

@Component
class ChannelPaser {
    private val uri2 = "https://t.me/s/xCareers"
    private val classifier = "tgme_widget_message_text js-message_text"
    private val classifier_parent = "tgme_widget_message force_userpic js-widget_message"
    private val photo_classifier = "tgme_widget_message_photo_wrap"
    private val video_classifier = "tgme_widget_message_video_player"
    private val logger: Logger = LoggerFactory.getLogger(ChannelPaser::class.java)

    @Autowired
    val channelService: ChannelService? = null

    @Autowired
    val tagUtil: TagUtil? = null

    @Autowired
    val messageService: MessageService?=null

    @Throws(IOException::class)
    fun parse(url: String = uri2) {
        val doc = Jsoup.connect(url).get()
      //  println(doc)
        val title = doc.getElementsByTag("title").html().replace(" – Telegram", "")
        val elements = doc.getElementsByClass(classifier_parent)
        val newUrls = mutableSetOf<String>()
        for (e in elements) {
            val txt = completeMessage(e, title)
            val key = e.attributes()["data-post"]
            val str = e.toString()// вот тут нужно анализировать на предмет наличия хэштэгов
            var channel = channelService?.getChannelByWeblink(url)
            if (channel!=null) {
                val tags = tagUtil?.getTagsFromText(str)

                if (!tags.isNullOrEmpty()) {
                    val messId = key.split("/").last().toLong()
                    val message = Message(
                            messageId = messId,
                            title = title,
                            text =  txt,
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
        val pictures = e.getElementsByClass(photo_classifier)
        val messages = e.getElementsByClass(classifier)
        val videos = e.getElementsByClass(video_classifier)
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

    fun getChannelUrl(e: Element): String? {
        val channelName = getChannelName(e)
        return "https://t.me/${channelName}"
    }

    fun getChannelName(e: Element): String? {
        val key = e.attributes()["data-post"]
        return key.split("/").first()
    }

    fun getChannelName(e: String?): String? {
        val elements = e?.split("/")
        if (elements?.size == 4)
            return elements.last()
        else if (elements?.size == 5)
            return elements[4]
        else throw IllegalArgumentException("Переданная строка не может быть распарсена корректно")
    }


    fun sendMsg(s: String?, url: String? = null) {
        val message = SendMessage()
        message.disableWebPagePreview()
        message.enableMarkdown(false)
        message.enableHtml(true)
        message.chatId = "" + 87927916
        message.text = s
        if (url != null) {
            val inlineKeyboardMarkup = InlineKeyboardMarkup()
            val keyboardButtonsRow1 = makeRedirectButton(url)
            inlineKeyboardMarkup.keyboard.add(keyboardButtonsRow1)
            message.replyMarkup = inlineKeyboardMarkup
        }


        CommandFactory.messageQueue.add(message)
    }

    fun makeRedirectButton(url: String, name: String = "Читать в источнике"): List<InlineKeyboardButton> {
        val inlineKeyboardButton = InlineKeyboardButton()
        inlineKeyboardButton.text = name
        inlineKeyboardButton.callbackData = "Button $name has been pressed"
        inlineKeyboardButton.url = url
        val keyboardButtonsRow1 = mutableListOf<InlineKeyboardButton>()
        keyboardButtonsRow1.add(inlineKeyboardButton)
        return keyboardButtonsRow1
    }
}