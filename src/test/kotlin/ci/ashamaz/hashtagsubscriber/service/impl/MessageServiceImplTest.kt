package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.model.Message
import ci.ashamaz.hashtagsubscriber.service.MessageService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime


@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
class MessageServiceImplTest : AbstractTransactionalJUnit4SpringContextTests() {

    @Autowired
    val messageService: MessageService? = null

    val message = Message(messageId = 32L, title = "hashtags", text = "hello #ashamaz")

    @Before
    fun setUp() {
        messageService?.save(message)
    }

    @Test
    fun isServiceAvailable() {
        assertNotNull(messageService)
    }

    @Test
    fun getMessageById() {
        val m = messageService?.getMessageById(1)
        assertNotNull(m)
    }

    @Test
    fun save() {
        val s = messageService?.save(message)
        assertNotNull(s?.id)
    }

    @Test
    fun delete() {
        val mess = Message(messageId = 33L, title = "test message", text = "hello #test #message")
        var m = messageService?.save(mess)
        assertNotNull(m)
        messageService?.delete(m!!)
        m = messageService?.getMessageById(m!!.id)
        assertNull(m)
    }

    @Test
    fun getAll() {
        val messages = messageService?.getAll()
        assertEquals(true, messages?.contains(message))
    }

    @Test
    fun getAllByDate() {
        val mess = messageService?.getAllByDate(LocalDate.now())
        assertEquals(true, mess?.contains(message))
    }

    @Test
    fun deleteAllOld() {
        var messOld: Message? = Message(messageId = 34L, title = "test message 1", date = LocalDateTime.of(2020, 1, 1, 12, 30), text = "old #message")
        var messNew: Message? = Message(messageId = 35L, title = "test message 2", text = "new #test #message")
        messOld = messageService?.save(messOld!!)
        messNew = messageService?.save(messNew!!)

        var all = messageService?.getAll()
        assertEquals(true, all?.contains(messOld))
        assertEquals(true, all?.contains(messNew))

        messageService?.deleteAllOld(2)
        all = messageService?.getAll()
        assertEquals(false, all?.contains(messOld))
        assertEquals(true, all?.contains(messNew))
    }

//    @Test
//    fun getAllByDays() {
//    }
//
//    @Test
//    fun getAllWithHashTag() {
//    }
//
//    @Test
//    fun getAllOfChannel() {
//    }
//
//    @Test
//    fun getAllOfChannelFiltered() {
//    }
}