package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.repository.ChannelRepository
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ChannelServiceImpl : ChannelService {

    @Autowired
    val channelRepo: ChannelRepository?=null

    override fun getChannelById(id: Long): Channel? {
        return channelRepo?.findByIdOrNull(id)
    }

    override fun getChannelByChatId(id: Long): Channel? {
        return channelRepo?.getByChatId(id)
    }

    override fun getChannelByName(name: String): Channel? {
        return channelRepo?.getByChannelName(name)
    }

    override fun getChannelByLink(name: String): Channel? {
        return channelRepo?.getChannelByLink(name)
    }

    override fun getChannelByWeblink(link: String): Channel? {
        return channelRepo?.getChannelByWeblink(link)
    }

    override fun getChannels(): List<Channel> {
        return channelRepo?.findAll().orEmpty()
    }

    override fun saveOrUpdate(channel: Channel) {
        channelRepo?.save(channel)
    }

    override fun removeChannel(channel: Channel) {
        channelRepo?.delete(channel)
    }
}