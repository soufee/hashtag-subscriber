package ci.ashamaz.hashtagsubscriber.service

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.model.Message
import java.time.LocalDate
import java.util.function.Predicate

interface MessageService {
    fun getMessageById(id: Long): Message?
    fun getByMessageIdAndChannel(messageId: Long, channel: Channel): Message?
    fun getMessageByLink(link: String): Message?
    fun save(message: Message): Message?
    fun delete(message: Message)
    fun getAll(): List<Message>
    fun getAllByDate(date: LocalDate): List<Message>
    fun deleteAllOld(daysBefore: Int)
    fun getAllByDays(days: Int): List<Message>
    fun getAllWithHashTag(hashtag: HashTag): List<Message>
    fun getAllOfChannel(channel: Channel): List<Message>
    fun getAllOfChannelFiltered(channel: Channel, filter: Predicate<Message>): List<Message>
}