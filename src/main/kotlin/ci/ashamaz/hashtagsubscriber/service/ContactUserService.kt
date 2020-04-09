package ci.ashamaz.hashtagsubscriber.service

import ci.ashamaz.hashtagsubscriber.model.ContactUser

interface ContactUserService {
    fun getCotactUserById(id: Int):ContactUser
    fun getCotactUserByUserName(name: String):ContactUser?
    fun getContactUserByChatId(id: Long): ContactUser?
    fun addContactUser(user: ContactUser)
}