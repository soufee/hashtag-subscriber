package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.repository.HashTagRepository
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional

class HashTagServiceImpl : HashTagService {

    @Autowired
    val repo: HashTagRepository?=null
    @Autowired
    val userService: ContactUserService?=null

    override fun getTagsByContactUser(user: ContactUser): List<HashTag> {
        return repo?.getHashTagsBySubscribers(user).orEmpty()
    }

    override fun saveTag(tag: HashTag) {
        val tagInDB = getByTag(tag.tag)
        if (tagInDB == null) {
            tag.lastMentionedDate = LocalDateTime.now()
            tag.registrationDate = LocalDateTime.now()
            repo?.save(tag)
        } else {
            tagInDB.lastMentionedDate = LocalDateTime.now()
            tagInDB.lastSubscribedDate = tag.lastSubscribedDate
            repo?.save(tagInDB)
        }
    }

    override fun getTag(id: Long): HashTag? {
        return repo?.findById(id)?.orElse(null)
    }

    override fun getAll(): List<HashTag> {
        return repo?.findAll().orEmpty()
    }

    override fun getByTag(tag: String): HashTag? {
        return repo?.getByTag(tag)
    }

    override fun subscribeTag(tag: String, user: ContactUser) {
        var hashtag = getByTag(tag)
        if (hashtag==null)
            hashtag = HashTag(tag = tag, lastSubscribedDate = LocalDateTime.now(), registrationDate = LocalDateTime.now())
        user.subscriptions.plusElement(hashtag)
        userService?.saveOrUpdateContactUser(user)

    }
}