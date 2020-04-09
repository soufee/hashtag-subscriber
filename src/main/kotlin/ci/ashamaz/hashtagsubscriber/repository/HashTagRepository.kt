package ci.ashamaz.hashtagsubscriber.repository

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.model.HashTag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface HashTagRepository : JpaRepository<HashTag, Long> {
    fun getHashTagsBySubscribers(user: ContactUser): List<HashTag>
}