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
    val messageQueue = ConcurrentLinkedQueue<BotApiMethod<TgmMessage>>()
    private final val HELP_MESSAGE: String = """
        *Доступные команды*
            - Для подписки на хэштэг или список хэштэгов, введите
            */subscribe* #хэштэг
            Если вы подписываетесь на несколько хэштэгов, их нужно перечислить через пробел. 
            Внимание! Решетка в названии хэштэга обязательна!
            
            - Для отписки введите
            */unsubscribe* #хэштэг
            Также можно отписаться списком через пробел
            
            - Для просмотра списка ваших подписок введите
            */mylist*
            
            - Для исключения конкретного канала из вашей рассылки введите
            */exclude* названиеКанала
        """.trimIndent()

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

    override fun processChannelPost(update: Update) {
        val post = update.channelPost

        val channelByChatId = channelService?.getChannelByChatId(post.chatId)
        if (channelByChatId?.banned==true) return
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
            mes.tags = tagUtil?.getTagsFromText(mes.text) ?: mutableSetOf()
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

        val contact = userConverter?.convert(user!!)
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

        when {
            text.startsWith("/subscribe") -> {
                if (text.contains('#')) {
                    subscribeTags(chatId, text)
                    showSubscribedList(chatId)

                } else {
                    sendMsg(chatId.toString(), """
                        *Неверная команда*
                        Для подписки на хэштэги нужно написать
                        /subscribe и через пробел перечислить список ваших хэштэгов, начиная со значка решетки '#'
                        Для описания доступных команд введите
                        /help
                    """.trimIndent())
                    showSubscribedList(chatId)
                }
            }
            text.startsWith("/unsubscribe") -> {
                if (text.contains('#')) {
                    unsubscribeTags(chatId, text)
                    showSubscribedList(chatId)
                } else {
                    sendMsg(chatId.toString(), """
                        Для отписки от хэштэгов нужно написать
                        /unsubscribe и через пробел перечислить список ваших хэштэгов, начиная со значка решетки '#'
                        Для описания доступных команд введите
                        /help
                    """.trimIndent())
                    showSubscribedList(chatId)

                }
            }
            text.startsWith("/exclude") -> {
                val groups = text.split(" ")
                if (groups.size < 2) {
                    //TODO ваши исключенные каналы
                    sendMsg(chatId.toString(), """
                        *Неверная команда*
                        Для исключения группы из вашей рассылки, нужно написать
                        /exclude и через пробел перечислить список групп, которые вы хотите исключить
                    """.trimIndent())
                } else {
                    for (i in 1 until groups.size) {
                        logger.debug("Исключаем ${groups[i]}")
                        //excludeGroup(text, chatId)
                        //TODO исключить из рассылки группу
                    }

                }

            }
            text.startsWith("/mylist") -> {
                showSubscribedList(chatId)
            }
            text.startsWith("/help") -> {
                sendHelpInfo(chatId = chatId)
            }
            else -> {
                sendHelpInfo("Команда не распознана", chatId)

            }
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

    private fun showSubscribedList(chatId: Long?) {
        if (chatId != null) {
            val user = userService?.getContactUserByChatId(chatId)
            val subscriptions = user?.subscriptions
            val list = subscriptions?.stream()?.map { it.tag }?.collect(Collectors.toList())?.joinToString(" ")
            sendMsg(chatId.toString(), """
                Список ваших подписок: 
                $list
            """.trimIndent())
        }
    }

    private fun sendHelpInfo(prefix: String = "", chatId: Long?) {
        sendMsg(chatId.toString(), prefix + "\n" + HELP_MESSAGE)
    }


    override fun getMessageList(): ConcurrentLinkedQueue<BotApiMethod<TgmMessage>> {
        return messageQueue
    }

    fun sendMsg(chatId: String?, s: String?) {
        val message = SendMessage()
        message.enableMarkdown(true)
        message.chatId = chatId
        message.text = s
        messageQueue.add(message)
    }

}