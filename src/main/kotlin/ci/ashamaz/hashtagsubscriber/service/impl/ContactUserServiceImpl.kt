package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.repository.ContactUserRepository
import ci.ashamaz.hashtagsubscriber.service.ContactUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ContactUserServiceImpl(@Autowired
                             val repo: ContactUserRepository): ContactUserService {

    @Transactional(readOnly = true)
    override fun getCotactUserById(id: Int): ContactUser {
        return repo.findById(id).orElse(null)
    }

    @Transactional(readOnly = true)
    override fun getCotactUserByUserName(name: String): ContactUser? {
        return repo.getContactUserByUserName(name)
    }

    @Transactional(readOnly = true)
    override fun getContactUserByChatId(id: Long): ContactUser? {
        return repo.getContactUserByChatId(id)
    }

    override fun addContactUser(user: ContactUser) {
        user.admin = (repo.count()==0L)
        repo.save(user)
    }
}