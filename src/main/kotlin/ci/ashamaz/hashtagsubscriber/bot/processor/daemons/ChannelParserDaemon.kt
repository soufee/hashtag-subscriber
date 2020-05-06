package ci.ashamaz.hashtagsubscriber.bot.processor.daemons

import org.slf4j.LoggerFactory
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import java.util.concurrent.ConcurrentLinkedQueue

class ChannelParserDaemon(val bot: TelegramLongPollingBot, val collection: ConcurrentLinkedQueue<BotApiMethod<Message>>) : Thread() {
    val logger = LoggerFactory.getLogger(MessageSenderDaemon::class.java)

    init {
        this.isDaemon = true
    }
    override fun run() {
        logger.info("Запущен поток парсинга каналов сообщений")
        while (true) {
            //TODO
        }
    }
}