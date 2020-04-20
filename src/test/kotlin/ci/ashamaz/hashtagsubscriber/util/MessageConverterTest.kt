package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.util.converter.MessageConverter
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.telegram.telegrambots.meta.api.objects.Message

@RunWith(MockitoJUnitRunner::class)
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

    val converter = MessageConverter()

    @Test
    fun convert() {
        val result = converter.convert(message)
        println(result)
        assertEquals(result.date.dayOfYear, 101)
        assertEquals(result.title, "hashtags")
        assertEquals(result.messageId, 26)
    }

}