package ci.ashamaz.hashtagsubscriber.repository

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import org.springframework.data.jpa.repository.JpaRepository

interface ContactUserRepository: JpaRepository<ContactUser, Int> {
    fun getContactUserByUserName(name: String): ContactUser?
    fun getContactUserByChatId(id: Long): ContactUser?
}