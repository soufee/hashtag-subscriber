package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.util.tag.TagUtil
import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import kotlin.streams.toList

@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
class TagUtilTest {


    @Autowired
    val tagUtil: TagUtil?=null

    @Test
    fun getTagsFromText() {
        val tags = tagUtil?.getTagsFromText (Literals.incomeMessage)
        assertNotNull(tags)
        tags!!
        assertNotEquals(0, tags.size)
        val tagValues = tags.stream().map { it.tag }.toList()
        assertEquals(tags.size, tagValues.size)
        assertTrue(tagValues.contains("#удаленка"))
        assertTrue(tagValues.contains("#москва"))
        assertTrue(tagValues.contains("#офис"))
        assertTrue(tagValues.contains("#санкт_петербург"))
        assertTrue(tagValues.contains("#россия"))
        assertTrue(tagValues.contains("#ios"))
        assertTrue(tagValues.contains("#разработка"))
    }
}