package ci.ashamaz.hashtagsubscriber.service

import ci.ashamaz.hashtagsubscriber.model.Channel

interface ChannelService {
    fun getChannelById(id: Long): Channel?
    fun getChannelByChatId(id: Long): Channel?
    fun getChannelByName(name: String): Channel?
    fun getChannelByLink(name: String): Channel?
    fun getChannelByWeblink(link: String): Channel?
    fun getChannels(): List<Channel>
    fun saveOrUpdate(channel: Channel): Channel?
    fun removeChannel(channel: Channel)
}