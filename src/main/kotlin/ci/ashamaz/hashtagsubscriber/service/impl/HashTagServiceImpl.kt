package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.repository.HashTagRepository
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class HashTagServiceImpl(@Autowired val repo: HashTagRepository) : HashTagService {
    override fun getTagsByContactUser(user: ContactUser): List<HashTag> {
        return repo.getHashTagsBySubscribers(user)
    }

    override fun saveTag(tag: HashTag) {
        repo.save(tag)
    }

    override fun getTag(id: Long): HashTag? {
        return repo.findById(id).orElse(null)
    }

    override fun getAll(): List<HashTag> {
        return repo.findAll()
    }
}