package ci.ashamaz.hashtagsubscriber.bot.processor

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update
import java.util.concurrent.ConcurrentLinkedQueue

interface ProcessPost {
    fun processChannelPost(update: Update)
    fun processPersonalPost(update: Update)
    fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<Message>>
}