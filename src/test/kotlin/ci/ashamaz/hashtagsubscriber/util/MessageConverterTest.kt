package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.util.converter.MessageConverter
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.telegram.telegrambots.meta.api.objects.Message


class MessageConverterTest: AbstractTransactionalJUnit4SpringContextTests() {
    final val gson = Gson()
    final val text = """{
  "messageId": 26,Ø
  "date": 1586515751,
  "chat": {
    "id": -1001471500488,
    "type": "channel",
    "title": "hashtags",
    "userName": "hashtagsubscribes"Ø
  },
  "text": "dffdf"
}
        """
    val message = gson.fromJson(text, Message::class.java)

    val converter = MessageConverter()

    @Test
    fun convert() {
        val result = converter.convert(message)
        assertEquals(result?.date?.dayOfYear, 101)
    }

}