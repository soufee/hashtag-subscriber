package ci.ashamaz.hashtagsubscriber.util

import ci.ashamaz.hashtagsubscriber.HashtagSubscriberApplication
import ci.ashamaz.hashtagsubscriber.model.Channel
import ci.ashamaz.hashtagsubscriber.service.ChannelService
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import ci.ashamaz.hashtagsubscriber.service.MessageService
import org.jsoup.Jsoup
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
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
class ChannelParserTest {

    @Autowired
    val channelParser: ChannelParser? = null

    @Autowired
    val channelService: ChannelService? = null

    @Autowired
    val tagService: HashTagService? = null

    @Autowired
    val messageService: MessageService? = null

    @Before
    fun initChannel() {
        val channel = Channel(
                link = "https://t.me/xCareers",
                weblink = "https://t.me/s/xCareers",
                channelName = "xCareers"
        )
        channelService?.saveOrUpdate(channel)
    }

    @Test
    fun parse() {
        channelParser?.parse(Literals.document!!)
        val channels = channelService?.getChannels()?.stream()?.map { t -> t.channelName }?.toList()
        assertNotNull(channels)
        channels!!
        assertTrue(channels.isNotEmpty())
        assertTrue(channels.contains("xCareers"))
        assertTrue(channels.contains("Levelller"))
        assertTrue(channels.contains("askamasaska"))

        val messages = messageService?.getAll()
        assertEquals(3, messages?.size)

        val tags = tagService?.getAll()?.stream()?.map { it.tag }?.toList()
        assertNotNull(tags)
        tags!!
        assertTrue(tags.isNotEmpty())
        tags.containsAll(arrayListOf("#удаленка",
                "#москва",
                "#санкт_петербург",
                "#разработка",
                "#ios",
                "#nodejs",
                "#офис",
                "#казань",
                "#россия",
                "#java")).let { assertTrue(it) }
    }

    @Test
    fun extractUrls() {
        val links = channelParser?.extractUrls(Literals.elementHtml)
        assertNotNull(links)
        links!!
        links.containsAll(arrayListOf(
                "https://t.me/xCareers",
                "https://t.me/Levelller"
        )).let { assertTrue(it) }
    }

    @Test
    fun completeMessage() {
        val message = channelParser?.completeMessage(Literals.element, "xCareers: Digital Jobs")
        assertEquals(message, Literals.shortenElementText)
//TODO сделать проверку с видео и фото

    }

    @Test
    fun getChannelUrl() {
        val url = channelParser?.getChannelUrl(Literals.element)
        assertEquals("https://t.me/xCareers", url)
    }

    @Test
    fun getChannelName() {
        val channelName = channelParser?.getChannelName(Literals.element)
        assertEquals("xCareers", channelName)
    }

    @Test
    fun getChannelNameString() {
        val channelName1 = channelParser?.getChannelName("https://t.me/xCareers")
        val channelName2 = channelParser?.getChannelName("https://t.me/s/xCareers")
        val channelName3 = channelParser?.getChannelName("https://t.me/s/xCareers/1251")
        assertEquals("xCareers", channelName1)
        assertEquals("xCareers", channelName2)
        assertEquals("xCareers", channelName3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun getChannelNameStringException() {
        val channelName2 = channelParser?.getChannelName("https://tele.graph.me/xCareers/1250")
    }

}