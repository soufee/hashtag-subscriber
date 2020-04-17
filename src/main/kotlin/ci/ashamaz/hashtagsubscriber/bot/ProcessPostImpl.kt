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
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import com.google.gson.Gson
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

@Component
class ProcessPostImpl : ProcessPost {
    private val logger: Logger = LoggerFactory.getLogger(ProcessPost::class.java)
    val messageQueue = ConcurrentLinkedQueue<BotApiMethod<TgmMessage>>()

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
    val tagUtil: TagUtil?=null

    override fun processChannelPost(update: Update) {
        val post = update.channelPost

        val channelByChatId = channelService?.getChannelByChatId(post.chatId)
        if (channelByChatId == null) {
            val channel = Channel(
                    chatId = post.chatId,
                    channelName = post.chat.userName
            )
            channelService?.saveOrUpdate(channel)
        }
        val gson = Gson()
        logger.debug(gson.toJson(post))
        val message = saveMessage(post)
        val tags = message?.tags
        val setOfUsers = mutableSetOf<ContactUser>()
        tags?.forEach {
            val hashtag = hashTagService?.getByTag(it.tag)
            val users = hashtag?.subscribers
            users?.forEach { setOfUsers.add(it) }
        }
        setOfUsers.forEach {
            val sm = ForwardMessage()
            sm.fromChatId = post.chatId.toString()
            sm.messageId = post.messageId
            sm.chatId = it.chatId.toString()
            messageQueue.add(sm)
        }

    }

    private fun saveMessage(post: TgmMessage): Message? {
        val mes = messageConverter?.convert(post)
        if (mes != null) {
            mes.tags = tagUtil?.getTagsFromText(mes.text)?: mutableSetOf()
            return messageService?.save(mes)
        }
        return null
    }

    override fun processPersonalPost(update: Update) {
        val text: String = update.getMessage()?.getText().toString()
        logger.debug(text)
        val message = update.message
        val user = message?.from
        val chatId = message?.chatId
        val contact = userConverter?.convert(user!!)
        if (contact != null) {
            if (chatId != null) {
                val c = userService?.getContactUserByChatId(chatId)
                if (c == null) {
                    contact.registrationDate = LocalDateTime.now()
                    userService?.saveOrUpdateContactUser(contact)
                }
            }

            messageQueue.add(sendMsg(chatId.toString(), "Ответ: $text"))
        } else {
            logger.info("Could not recognize a User or chat id")
            throw IllegalArgumentException("Could not recognize a User or chat id")
        }

    }

    override fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<TgmMessage>> {
        return messageQueue
    }

    fun sendMsg(chatId: String?, s: String?): SendMessage {
        val message = SendMessage()
        message.enableMarkdown(true)
        message.chatId = chatId
        message.text = s
        return message
    }

}