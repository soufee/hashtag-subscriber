package ci.ashamaz.hashtagsubscriber.bot.processor

import ci.ashamaz.hashtagsubscriber.bot.processor.Command

interface CommandExecutor {
    fun executeCommand(command: Command, chatId: Long, messageId: Int?, text: String)
}