package ci.ashamaz.hashtagsubscriber.bot

import ci.ashamaz.hashtagsubscriber.bot.processor.ProcessPostImpl
import ci.ashamaz.hashtagsubscriber.bot.processor.daemons.MessageSender
import ci.ashamaz.hashtagsubscriber.bot.processor.intrfc.ProcessPost
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
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
        if (update == null) return
        if (update.hasChannelPost()) {
            processor?.processChannelPost(update)
        } else {
            processor?.processPersonalPost(update, this)
        }
    }


    @PostConstruct
    fun connected() {
        logger.info("Бот запущен")
        if (processor is ProcessPostImpl) {
            (processor as ProcessPostImpl).commandExecutor?.registerBot(this)
        }
        val collection = processor?.getMessageList()
        if (collection != null) {
            MessageSender(this, collection).start()
        } else {
            logger.error("Не удалось запустить поток обработки исходящих сообщений")
        }
    }
}