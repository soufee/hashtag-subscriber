package ci.ashamaz.hashtagsubscriber.repository

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface ContactUserRepository: JpaRepository<ContactUser, Long> {
    fun getContactUserByUserName(name: String): ContactUser?
    fun getContactUserByChatId(id: Long): ContactUser?
}