package ci.ashamaz.hashtagsubscriber

import ci.ashamaz.hashtagsubscriber.service.impl.ChannelServiceImplTest
import ci.ashamaz.hashtagsubscriber.util.MessageConverterTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@RunWith(Suite::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
@ActiveProfiles("test")
@Suite.SuiteClasses(
        MessageConverterTest::class,
        ChannelServiceImplTest::class
)
class HashtagSubscriberApplicationTests {

    @Test
    fun contextLoads() {
    }

}
