package ci.ashamaz.hashtagsubscriber.repository

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository: JpaRepository<Message, Long> {
    fun getByChannel(channel: Channel): List<Message>
    fun getAllByTagsContains(tag: HashTag): List<Message>
    fun getByMessageIdAndChannel(messageId: Long, channel: Channel): Message?
    fun getMessageByLink(link: String): Message?
    fun getMessageByWeblink(link: String): Message?
}