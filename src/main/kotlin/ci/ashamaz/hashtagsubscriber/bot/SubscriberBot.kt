package ci.ashamaz.hashtagsubscriber.bot

import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.util.UserConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import java.util.logging.Level
import java.util.logging.Logger
import javax.annotation.PostConstruct

@Component
@PropertySource("classpath:bot.properties")
class SubscriberBot : TelegramLongPollingBot() {
    final val log = Logger.getLogger("SubscriberBot")

    @Autowired
    private val userConverter: UserConverter? = null

    @Autowired
    private val service: ContactUserService? = null

    @Value("\${bot.name}")
    var botName: String? = null

    @Value("\${bot.token}")

    var token: String? = null


    override fun getBotUsername(): String {
        return botName ?: ""

    }

    override fun getBotToken(): String {
        return token ?: ""

    }

    override fun onUpdateReceived(update: Update?) {
        val message: String = update?.getMessage()?.getText().toString()
        log.log(Level.INFO, message)
        val user = update?.message?.from
        val chatId = update?.message?.chatId

        var contact = userConverter?.convert(user)
        contact?.subscriptions?.plus(HashTag(tag="qwerty"))
        if (contact != null) {
            if (chatId != null) {
                val c = service?.getContactUserByChatId(chatId) != null
                if (!c) service?.addContactUser(contact)
            }

        }

        sendMsg(chatId.toString(), "Ответ: " + message)
    }

//    @Synchronized
    fun sendMsg(chatId: String?, s: String?) {
      Runnable {
          val message = SendMessage()
          message.enableMarkdown(true)
          message.chatId = chatId
          message.text = s
          try {

              execute(message)
          } catch (e: TelegramApiException) {
              log.log(Level.SEVERE, "Exception: ", e.toString())
          }
      }.run()

    }

    @PostConstruct
    fun connected() {
        log.log(Level.INFO, "Бот запущен")
    }
}