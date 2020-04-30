package ci.ashamaz.hashtagsubscriber.bot.processor.intrfc

import ci.ashamaz.hashtagsubscriber.bot.processor.enums.Command
import org.telegram.telegrambots.bots.TelegramLongPollingBot

interface CommandExecutor {
    fun executeCommand(command: Command, chatId: Long, messageId: Int?, text: String)
    fun registerBot(bot: TelegramLongPollingBot)
}