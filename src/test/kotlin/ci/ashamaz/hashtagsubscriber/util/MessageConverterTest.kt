package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.util.converter.MessageConverter
import com.google.gson.Gson
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.telegram.telegrambots.meta.api.objects.Message

@ActiveProfiles("test")
@ContextConfiguration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = [HashtagSubscriberApplication::class])
class MessageConverterTest {
    final val gson = Gson()
    final val text = """{
  "messageId": 26,
  "date": 1586515751,
  "chat": {
    "id": -1001471500488,
    "type": "channel",
    "title": "hashtags",
    "userName": "hashtagsubscribes"
  },
  "text": "dffdf"
}
        """
    val message = gson.fromJson(text, Message::class.java)

    @Autowired
    val converter: MessageConverter? = null

    @Test
    fun convert() {
        val result = converter?.convert(message)
        assertNotNull(result)
        println(result)
        assertEquals(result?.date?.dayOfYear, 101)
        assertEquals(result?.title, "hashtags")
        assertEquals(result?.messageId, 26L)
    }

}