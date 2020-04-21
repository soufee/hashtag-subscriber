package ci.ashamaz.hashtagsubscriber.bot

interface CommandExecutor {
    fun executeCommand(command: Command, chatId: Long, messageId: Int?, text: String)
}