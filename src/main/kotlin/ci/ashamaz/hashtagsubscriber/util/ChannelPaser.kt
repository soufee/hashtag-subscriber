package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.bot.processor.CommandFactory
import ci.ashamaz.hashtagsubscriber.util.extention.shorten
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.safety.Whitelist
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import java.io.IOException
import kotlin.streams.toList

@Component
class ChannelPaser {
    private val uri2 = "https://t.me/s/shevchenkomax_1"
    private val classifier = "tgme_widget_message_text js-message_text"
    private val classifier_parent = "tgme_widget_message force_userpic js-widget_message"
    private val photo_classifier = "tgme_widget_message_photo_wrap"
    private val video_classifier = "tgme_widget_message_video_player"

    @Throws(IOException::class)
    fun parse(url: String = uri2) {
        val doc = Jsoup.connect(url).get()
        val title = doc.getElementsByTag("title").html().replace(" – Telegram", "")
        val elements = doc.getElementsByClass(classifier_parent)
        val newUrls = mutableSetOf<String>()
        for (e in elements) {
            val txt = completeMessage(e, title)
            val key = e.attributes()["data-post"]
            val str = e.toString()// вот тут нужно анализировать на предмет наличия хэштэгов

            val linksFromMessages = extractUrls(str) as MutableSet
            val channelUrl = getChannelName(e)
            linksFromMessages.remove(channelUrl)
            newUrls.addAll(linksFromMessages)
        }

        println(newUrls.joinToString("\n"))
    }

    private fun extractUrls(str: String): Set<String> {
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

    private fun completeMessage(e: Element, title: String): String {
        val channelUrl = getChannelName(e)
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

    private fun getChannelName(e: Element): String? {
        val key = e.attributes()["data-post"]
        val channelName = key.split("/").first()
        return "https://t.me/${channelName}"
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