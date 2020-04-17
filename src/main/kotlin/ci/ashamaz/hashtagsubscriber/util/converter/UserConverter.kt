package ci.ashamaz.hashtagsubscriber.util.converter

import org.telegram.telegrambots.meta.api.objects.User
import ci.ashamaz.hashtagsubscriber.model.ContactUser
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component

@Component
class UserConverter: Converter<User?, ContactUser?>{
    override fun convert(tgmUser: User?): ContactUser? {
        if (tgmUser==null) return null
        return ContactUser(
                firstName = tgmUser.firstName,
                lastName = tgmUser.lastName,
                isBot = tgmUser.bot,
                userName = tgmUser.userName,
                languageCode= tgmUser.languageCode,
                chatId = tgmUser.id.toLong()
        )
    }
}