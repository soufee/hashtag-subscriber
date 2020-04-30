package ci.ashamaz.hashtagsubscriber.bot.processor.daemons

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import java.util.concurrent.ConcurrentLinkedQueue

class MessageSender(val bot: TelegramLongPollingBot, val collection: ConcurrentLinkedQueue<BotApiMethod<Message>>) : Thread() {
    val logger = LoggerFactory.getLogger(MessageSender::class.java)

    init {
        this.isDaemon = true
    }

    override fun run() {
        while (true) {
            while (!collection.isNullOrEmpty()) {
                bot.execute(collection.poll())
            }
            sleep(3000)
        }
    }
}