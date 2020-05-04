package ci.ashamaz.hashtagsubscriber

import ci.ashamaz.hashtagsubscriber.service.impl.ChannelServiceImplTest
import ci.ashamaz.hashtagsubscriber.service.impl.MessageServiceImplTest
import ci.ashamaz.hashtagsubscriber.util.MessageConverterTest
import ci.ashamaz.hashtagsubscriber.util.TagUtilTest
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
        ChannelServiceImplTest::class,
        MessageServiceImplTest::class,
        TagUtilTest::class

)
class HashtagSubscriberApplicationTests {

    @Test
    fun contextLoads() {
    }

}
