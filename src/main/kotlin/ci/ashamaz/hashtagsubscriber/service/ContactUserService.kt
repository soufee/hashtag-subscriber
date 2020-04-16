package ci.ashamaz.hashtagsubscriber.service

import ci.ashamaz.hashtagsubscriber.model.ContactUser

interface ContactUserService {
    fun getCotactUserById(id: Long):ContactUser
    fun getCotactUserByUserName(name: String):ContactUser?
    fun getContactUserByChatId(id: Long): ContactUser?
    fun saveOrUpdateContactUser(user: ContactUser)
}