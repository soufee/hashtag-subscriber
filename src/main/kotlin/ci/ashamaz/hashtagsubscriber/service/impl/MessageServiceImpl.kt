package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.model.Message
import ci.ashamaz.hashtagsubscriber.repository.MessageRepository
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import ci.ashamaz.hashtagsubscriber.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.util.function.Predicate
import kotlin.streams.toList

@Service
@Transactional
class MessageServiceImpl : MessageService {
    @Autowired
    val messageRepo: MessageRepository? = null

    @Autowired
    val hashTagService: HashTagService? = null

    override fun getMessageById(id: Long): Message? {
        return messageRepo?.getOne(id)
    }

    override fun save(message: Message): Message? {
        if (!message.tags.isEmpty()) {
            message.tags.forEach { hashTagService?.saveTag(it) }
        }
        return messageRepo?.save(message)
    }

    override fun delete(message: Message) {
        if (messageRepo?.existsById(message.id) == true)
            messageRepo?.delete(message)
    }

    override fun getAll(): List<Message> {
        return messageRepo?.findAll().orEmpty()
    }

    override fun getAllByDate(date: LocalDate): List<Message> {
        return getAll().stream().filter { it.date.dayOfYear == date.dayOfYear }.toList()
    }

    @Transactional
    override fun deleteAllOld(daysBefore: Int) {
        val list = getAll().stream().filter { it.date.dayOfYear <= LocalDate.now().minusDays(daysBefore.toLong()).dayOfYear }.toList()
        list.forEach { messageRepo?.delete(it) }
    }

    override fun getAllByDays(days: Int): List<Message> {
        return getAll().stream().filter { it.date.dayOfYear >= LocalDate.now().minusDays(days.toLong()).dayOfYear }.toList()
    }

    override fun getAllWithHashTag(hashtag: HashTag): List<Message> {
        return messageRepo?.getAllByTagsContains(hashtag).orEmpty()
    }

    override fun getAllOfChannel(channel: Channel): List<Message> {
        return messageRepo?.getByChannel(channel).orEmpty()
    }

    override fun getAllOfChannelFiltered(channel: Channel, filter: Predicate<Message>): List<Message> {
        return getAllOfChannel(channel).stream().filter(filter).toList()
    }
}