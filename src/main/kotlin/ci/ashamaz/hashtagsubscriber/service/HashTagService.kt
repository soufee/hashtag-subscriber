package ci.ashamaz.hashtagsubscriber.service

import ci.ashamaz.hashtagsubscriber.model.ContactUser
import ci.ashamaz.hashtagsubscriber.model.HashTag

interface HashTagService {
    fun getTagsByContactUser(user: ContactUser): List<HashTag>
    fun saveTag(tag: HashTag)
    fun getTag(id: Long): HashTag?
    fun getAll():List<HashTag>
    fun getByTag(tag: String): HashTag?
    fun subscribeTag(tag: String, user: ContactUser)
}