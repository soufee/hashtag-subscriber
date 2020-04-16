package ci.ashamaz.hashtagsubscriber.bot


import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import javax.annotation.PostConstruct

@Component
@PropertySource("classpath:bot.properties")
class SubscriberBot : TelegramLongPollingBot() {
    private val logger: Logger = LoggerFactory.getLogger(SubscriberBot::class.java)

    @Autowired
    val processor: ProcessPost? = null

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
        Runnable {
            if (update == null) return@Runnable
            var method: BotApiMethod<*>? = null
            if (update.hasChannelPost()) {
                method = processor?.processChannelPost(update)
            } else {
                method = processor?.processPersonalPost(update)
            }
            executor(method)
        }.run()
    }

    fun executor(method: BotApiMethod<Message>?) {
        execute(method)
    }

    @PostConstruct
    fun connected() {
        logger.info("Бот запущен")
    }
}