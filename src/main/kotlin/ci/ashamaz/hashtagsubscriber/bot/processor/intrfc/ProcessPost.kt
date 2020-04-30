package ci.ashamaz.hashtagsubscriber.bot.processor.intrfc

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.concurrent.ConcurrentLinkedQueue

interface ProcessPost {
    fun processChannelPost(update: Update)
    fun processPersonalPost(update: Update, bot: TelegramLongPollingBot)
    fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<Message>>
}