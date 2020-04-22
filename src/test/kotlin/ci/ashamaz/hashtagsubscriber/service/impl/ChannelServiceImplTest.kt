package ci.ashamaz.hashtagsubscriber.service.impl

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import com.github.springtestdbunit.DbUnitTestExecutionListener
import com.github.springtestdbunit.annotation.DatabaseSetup
import org.junit.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner

@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
//@TestExecutionListeners(DbUnitTestExecutionListener::class)
//@DatabaseSetup("/data/ChannelServiceImplTest-channel.xml")
class ChannelServiceImplTest: AbstractTransactionalJUnit4SpringContextTests() {

    @Autowired
    val channelService: ChannelService? = null

    @Test
    fun isServiceAvailable(){
        assertNotNull(channelService)
    }

    @Test
    fun getChannelById() {
        assertTrue(true)
    }
//
//    @Test
//    fun getChannelByChatId() {
//    }
//
//    @Test
//    fun getChannelByName() {
//    }
//
//    @Test
//    fun getChannels() {
//    }
//
//    @Test
//    fun saveOrUpdate() {
//    }
//
//    @Test
//    fun removeChannel() {
//    }
//
//    @Test
//    fun getChannelRepo() {
//    }
}