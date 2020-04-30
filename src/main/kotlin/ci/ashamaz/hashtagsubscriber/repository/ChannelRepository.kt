package ci.ashamaz.hashtagsubscriber.repository

import ci.ashamaz.hashtagsubscriber.model.Channel
import org.springframework.data.jpa.repository.JpaRepository

interface ChannelRepository: JpaRepository<Channel, Long> {
    fun getByChatId(chatId: Long): Channel?
    fun getByChannelName(name: String): Channel?
    fun getChannelByLink(name: String): Channel?
    fun getChannelByWeblink(link: String): Channel?
}