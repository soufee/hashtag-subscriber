package ci.ashamaz.hashtagsubscriber.bot

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.stream.Collectors


@Component
class CommandFactory : CommandExecutor {

    @Autowired
    val userService: ContactUserService? = null
    @Autowired
    var channelService: ChannelService? = null
    @Autowired
    var hashTagService: HashTagService? = null

    val logger = LoggerFactory.getLogger(CommandFactory::class.java)
    val commands: EnumMap<Command, (Long, String, Int?) -> Unit> = EnumMap<Command, (Long, String, Int?) -> Unit>(Command::class.java)

    init {
        commands[Command.LIST] = { c, t, m ->
            run {
                showSubscribedList(c)
            }
        }
        commands[Command.HELP] = { c, t, m ->
            run {
                sendHelpInfo("", null, c)
            }
        }
        commands[Command.EXCLUDE] = { c, t, m ->
            run { excludeChannel(t, c, m) }
        }
        commands[Command.SUBSCRIBE] = { c, t, m ->
            run { subscribe(c, t, m) }
        }
        commands[Command.UNSUBSCRIBE] = { c, t, m ->
            run { unsubscribe(c, t, m) }
        }
        commands[Command.INCLUDE] = { c, t, m ->
            run { includeChannel(c, t, m) }
        }
        commands[Command.ADMIN] = { c, t, m ->
            run { openAdminTools(c, t, m) }
        }
    }

    override fun executeCommand(command: Command, chatId: Long, messageId: Int?, text: String) {
        logger.debug("Command $command, chatId $chatId, messageId $messageId, text $text")
        commands[command]?.invoke(chatId, text, messageId)
    }

    private fun openAdminTools(chatId: Long, text: String, m: Int?) {
        val user = userService?.getContactUserByChatId(chatId)
        if (user?.admin!=true)
        replyMessage(chatId = chatId, messageId = m, text = "команда не доступна")
        else
        replyMessage(chatId = chatId, messageId = m, text = "команда еще не поддерживается")
    }

    private fun includeChannel(chatId: Long, text: String, m: Int?) {
       // replyMessage(chatId = chatId, messageId = m, text = "команда еще не поддерживается")
        val userByChatId = userService?.getContactUserByChatId(chatId) ?: return
        val groups = text.split(" ")
        if (groups.size < 2) {
            replyMessage(chatId.toString(), m, """
                        Для разблокировки группы из вашей рассылки, нужно написать
                        /include и через пробел перечислить список групп, которые вы хотите разблокировать
                    """.trimIndent())
            showExcludedChannels(chatId)
        } else {
            val wrongs = mutableListOf<String>()
            for (i in 1 until groups.size) {
                val channel = channelService?.getChannelByName(groups[i])
                if (channel != null) {
                    val excluded = userByChatId.excludedChannels as MutableSet<Channel>
                    excluded.remove(channel)
                } else {
                    wrongs.add(groups[i])
                }
            }
            userService?.saveOrUpdateContactUser(userByChatId)
            if (wrongs.size > 0) {
                val wrongChannelNames = wrongs.joinToString(" ")
                sendMsg(chatId.toString(), """
                    Введенные названия каналов не найдены
                    $wrongChannelNames
                """.trimIndent())
            }
            showExcludedChannels(chatId)
        }
    }

    private fun unsubscribe(chatId: Long, text: String, m: Int?) {
        logger.debug("Unsubscribe chatId $chatId, text $text")
        if (text.contains('#')) {
            unsubscribeTags(chatId, text)
            showSubscribedList(chatId)
        } else {
            replyMessage(chatId.toString(),m, """
                        Для отписки от хэштэгов нужно написать
                        /unsubscribe и через пробел перечислить список ваших хэштэгов, начиная со значка решетки '#'
                        Для описания доступных команд введите
                        /help
                    """.trimIndent())
            showSubscribedList(chatId)

        }
    }

    private fun unsubscribeTags(chatId: Long?, text: String) {
        val tags = getTagsFromText(text)
        if (chatId != null) {
            val user = userService?.getContactUserByChatId(chatId)
            if (user != null) {
                val subscriptions = user.subscriptions as MutableSet<HashTag>
                tags.forEach { subscriptions.remove(it) }
                userService?.saveOrUpdateContactUser(user)
            }
        }
    }

    private fun subscribe(chatId: Long, text: String, m: Int?) {
        logger.debug("subscribe chatId $chatId, text $text")
        if (text.contains('#')) {
            subscribeTags(chatId, text)
            showSubscribedList(chatId)

        } else {
            replyMessage(chatId.toString(), m, """
                        *Неверная команда*
                        Для подписки на хэштэги нужно написать
                        /subscribe и через пробел перечислить список ваших хэштэгов, начиная со значка решетки '#'
                        Для описания доступных команд введите
                        /help
                    """.trimIndent())
            showSubscribedList(chatId)
        }
    }

    private fun subscribeTags(chatId: Long?, text: String) {
        val tags = getTagsFromText(text)
        if (chatId != null) {
            val user = userService?.getContactUserByChatId(chatId)
            if (user != null) {
                val subscriptions = user.subscriptions as MutableSet<HashTag>
                tags.forEach { subscriptions.add(it) }
                userService?.saveOrUpdateContactUser(user)
            }
        }
    }


    private fun getTagsFromText(text: String): Set<HashTag> {
        val arguments = text.split(" ")
        val tags = mutableSetOf<HashTag>()
        arguments.forEach {
            if (it.startsWith('#')) {
                val tag = hashTagService?.getByTag(it)
                if (tag != null) tags.add(tag)
                else {
                    val tag = HashTag(tag = it, registrationDate = LocalDateTime.now(), lastSubscribedDate = LocalDateTime.now())
                    tags.add(tag)
                }
            }
        }
        return tags
    }


    private fun excludeChannel(text: String, chatId: Long?, m: Int?) {
        if (chatId == null) return
        val userByChatId = userService?.getContactUserByChatId(chatId) ?: return


        val groups = text.split(" ")
        if (groups.size < 2) {
            replyMessage(chatId.toString(), m, """
                        Для исключения группы из вашей рассылки, нужно написать
                        /exclude и через пробел перечислить список групп, которые вы хотите исключить
                    """.trimIndent())
            showExcludedChannels(chatId)
        } else {
            val wrongs = mutableListOf<String>()
            for (i in 1 until groups.size) {
                val channel = channelService?.getChannelByName(groups[i])
                if (channel != null) {
                    val excluded = userByChatId.excludedChannels as MutableSet<Channel>
                    excluded.add(channel)
                } else {
                    wrongs.add(groups[i])
                }
            }
            userService?.saveOrUpdateContactUser(userByChatId)
            if (wrongs.size > 0) {
                val wrongChannelNames = wrongs.joinToString(" ")
                sendMsg(chatId.toString(), """
                    Введенные названия каналов не найдены
                    $wrongChannelNames
                """.trimIndent())
            }
            showExcludedChannels(chatId)
        }
    }

    private fun excludedChannelNames(chatId: Long): Set<Channel> {
        return userService?.getContactUserByChatId(chatId)?.excludedChannels.orEmpty()
    }

    private fun showExcludedChannels(chatId: Long) {
        val excluded = excludedChannelNames(chatId)
        val text = "Список ваших исключенных каналов пуст"
        if (excluded.isNullOrEmpty()) {
            sendMsg(chatId.toString(), text)
        } else {
            sendMsg(chatId.toString(), """
                Список ваших исключенных каналов
                ${excluded.stream().map { it.channelName }.collect(Collectors.toList()).joinToString(" ")}
            """.trimIndent())
        }
    }


    private fun showSubscribedList(chatId: Long?) {
        if (chatId != null) {
            val user = userService?.getContactUserByChatId(chatId)
            val subscriptions = user?.subscriptions.orEmpty()
            if (subscriptions.isNotEmpty()) {
                val list = subscriptions.stream().map { it.tag }?.collect(Collectors.toList())?.joinToString(" ")
                sendMsg(chatId.toString(), """
                Список ваших подписок: 
                $list
            """.trimIndent())
            } else sendMsg(chatId.toString(), "Список ваших подписок пуст")
        }
    }

    companion object {
        private val HELP_MESSAGE: String = """
        *Доступные команды*
            - Для подписки на хэштэг или список хэштэгов, введите
            */subscribe* #хэштэг
            Если вы подписываетесь на несколько хэштэгов, их нужно перечислить через пробел. 
            Внимание! Решетка в названии хэштэга обязательна!
            
            - Для отписки введите
            */unsubscribe* #хэштэг
            Также можно отписаться списком через пробел
            
            - Для просмотра списка ваших подписок введите
            */list*
            
            - Для исключения конкретного канала из вашей рассылки введите
            */exclude* названиеКанала
        """.trimIndent()

        val messageQueue = ConcurrentLinkedQueue<BotApiMethod<Message>>()

        fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<Message>> {
            return messageQueue
        }

        fun sendMsg(chatId: String?, s: String?) {
            val message = SendMessage()
            message.enableMarkdown(true)
            message.chatId = chatId
            message.text = s
            messageQueue.add(message)
        }

        fun replyMessage(chatId: Long, messageId: Int?, text: String) {
            replyMessage(chatId.toString(), messageId, text)
        }

        fun replyMessage(chatId: String, messageId: Int?, text: String) {
            if (messageId == null) sendMsg(chatId, text)
            else {
                val sendMessage = SendMessage()
                sendMessage.enableMarkdown(true)
                sendMessage.chatId = chatId
                sendMessage.replyToMessageId = messageId
                sendMessage.text = text
                messageQueue.add(sendMessage)
            }

        }

        fun sendHelpInfo(prefix: String = "", messageId: Int?, chatId: Long?) {
            replyMessage(chatId.toString(), messageId, prefix + "\n" + HELP_MESSAGE)
        }
    }
}