package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.repository.ContactUserRepository
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class ContactUserServiceImpl(@Autowired
                             val repo: ContactUserRepository,
                             @Autowired
                             val hashTagService: HashTagService) : ContactUserService {

    override fun getCotactUserById(id: Long): ContactUser {
        return repo.findById(id).orElse(null)
    }

    override fun getCotactUserByUserName(name: String): ContactUser? {
        return repo.getContactUserByUserName(name)
    }

    @Transactional
    override fun getContactUserByChatId(id: Long): ContactUser? {
        return repo.getContactUserByChatId(id)
    }

    override fun saveOrUpdateContactUser(user: ContactUser) {
        user.admin = (repo.count() == 0L)
        if (user.subscriptions.isNotEmpty()) {
            user.subscriptions.forEach {
                it.lastSubscribedDate = LocalDateTime.now()
                hashTagService.saveTag(it)
            }
        }
        repo.save(user)
    }
}