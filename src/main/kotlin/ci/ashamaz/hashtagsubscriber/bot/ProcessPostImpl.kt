package ci.ashamaz.hashtagsubscriber.bot

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.BotApiMethod
import org.telegram.telegrambots.meta.api.methods.ForwardMessage
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import ci.ashamaz.hashtagsubscriber.model.Message
import ci.ashamaz.hashtagsubscriber.service.MessageService
import org.telegram.telegrambots.meta.api.objects.Message as TgmMessage
import org.telegram.telegrambots.meta.api.objects.User
import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import com.google.gson.Gson
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.stream.Collectors

@Component
class ProcessPostImpl : ProcessPost {
    private val logger: Logger = LoggerFactory.getLogger(ProcessPost::class.java)

    @Autowired
    val channelService: ChannelService? = null

    @Autowired
    val messageConverter: Converter<TgmMessage?, Message?>? = null

    @Autowired
    val userConverter: Converter<User?, ContactUser?>? = null

    @Autowired
    val messageService: MessageService? = null

    @Autowired
    val userService: ContactUserService? = null

    @Autowired
    val hashTagService: HashTagService? = null

    @Autowired
    val tagUtil: TagUtil? = null

    @Autowired
    val commandExecutor: CommandExecutor? = null

    override fun processChannelPost(update: Update) {
        val post = update.channelPost

        val channelByChatId = channelService?.getChannelByChatId(post.chatId)
        if (channelByChatId?.banned == true) return
        if (channelByChatId == null) {
            val channel = Channel(
                    chatId = post.chatId,
                    channelName = post.chat.userName,
                    link = "https://t.me/"+post.chat.userName
            )
            channelService?.saveOrUpdate(channel)
        }
        val message = saveMessage(post)
        val tags = message?.tags
        val setOfUsers = mutableSetOf<ContactUser>()
        tags?.forEach { it ->
            val hashtag = hashTagService?.getByTag(it.tag)
            val users = hashtag?.subscribers?.filter { !it.excludedChannels.contains(channelByChatId) }
            users?.forEach { setOfUsers.add(it) }
        }
        setOfUsers.forEach {
            val sm = ForwardMessage()
            sm.fromChatId = post.chatId.toString()
            sm.messageId = post.messageId
            sm.chatId = it.chatId.toString()
            CommandFactory.messageQueue.add(sm)
        }

    }

    private fun saveMessage(post: TgmMessage): Message? {
        val mes = messageConverter?.convert(post)
        if (mes != null) {
            val text = mes.text?:"" + mes.caption?:""
            mes.tags = tagUtil?.getTagsFromText(text) ?: mutableSetOf()
            return messageService?.save(mes)
        }
        return null
    }

    override fun processPersonalPost(update: Update) {
        val text: String = update.message?.text.toString()
        logger.debug(text)
        val message = update.message
        val user = message?.from
        val chatId = message?.chatId
        var contact: ContactUser? = null
        if (user != null) {
            contact = userConverter?.convert(user)
        }
        if (contact != null) {
            if (chatId != null) {
                val c = userService?.getContactUserByChatId(chatId)
                if (c == null) {
                    contact.registrationDate = LocalDateTime.now()
                    userService?.saveOrUpdateContactUser(contact)
                }
            }
        } else {
            logger.info("Could not recognize a User or chat id")
            throw IllegalArgumentException("Could not recognize a User or chat id")
        }
        if (chatId == null) return
        when {
            text.startsWith("/subscribe") -> {
                commandExecutor?.executeCommand(Command.SUBSCRIBE, chatId, message.messageId, text)
            }
            text.startsWith("/unsubscribe") -> {
                commandExecutor?.executeCommand(Command.UNSUBSCRIBE, chatId, message.messageId, text)
            }
            text.startsWith("/exclude") -> {
                commandExecutor?.executeCommand(Command.EXCLUDE, chatId, message.messageId, text)
            }
            text.startsWith("/include") -> {
                commandExecutor?.executeCommand(Command.INCLUDE, chatId, message.messageId, text)
            }
            text.startsWith("/list") -> {
                commandExecutor?.executeCommand(Command.LIST, chatId, message.messageId, text)
            }
            text.startsWith("/help") -> {
                commandExecutor?.executeCommand(Command.HELP, chatId, message.messageId, text)
            }
            text.startsWith("/admin") -> {
                commandExecutor?.executeCommand(Command.ADMIN, chatId, message.messageId, text)
            }
            else -> {
                CommandFactory.sendHelpInfo("Команда не распознана", message.messageId, chatId)
            }
        }
    }


    override fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<TgmMessage>> {
        return CommandFactory.messageQueue
    }
}