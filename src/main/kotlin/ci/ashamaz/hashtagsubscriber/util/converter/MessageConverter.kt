package ci.ashamaz.hashtagsubscriber.util.converter

import ci.ashamaz.hashtagsubscriber.model.Channel
import org.springframework.core.convert.converter.Converter
import ci.ashamaz.hashtagsubscriber.model.Message
import org.telegram.telegrambots.meta.api.objects.Message as TgmMessage
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

@Component
class MessageConverter: Converter<TgmMessage, Message> {
    @Autowired
    val channelService: ChannelService?=null
    override fun convert(source: TgmMessage): Message {
        return Message(
                messageId = source.messageId.toLong(),
                date = getLocalDate(source.date),
                text = source.text,
                title = source.chat.title,
                channel = getChannel(source.chatId),
                caption = source.caption,
                link = getChannel(source.chatId)?.link+"/"+source.messageId
        )
    }

    private fun getChannel(chatId: Long): Channel? {
        return channelService?.getChannelByChatId(chatId)
    }

    private fun getLocalDate(date: Int?): LocalDateTime {
        val date1 = Date(date!!.toLong()*1000)
        val localDate =  date1.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return LocalDateTime.of(localDate, LocalTime.now())
    }
}