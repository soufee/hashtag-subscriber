package ci.ashamaz.hashtagsubscriber.bot

import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

interface ProcessPost {
    fun processChannelPost(update: Update): BotApiMethod<Message>?
    fun processPersonalPost(update: Update): BotApiMethod<Message>?
}