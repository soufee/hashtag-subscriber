package ci.ashamaz.hashtagsubscriber.util.tag

import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.service.HashTagService
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TagUtil {
    @Autowired
    val hashtagService: HashTagService? = null

    fun getTagsFromText(text: String): Set<HashTag> {
        val result = mutableSetOf<HashTag>()
        if (text.contains('#')) {
            val cleanText = Jsoup.clean(text, Whitelist.simpleText())
            val words = cleanText.split("[\\s\n.,\\-]".toRegex())
            for (s in words) {
                val ss = s.replace("[^#a-zA-Zа-яА-Я0-9_]".toRegex(), "").trim().toLowerCase().intern()
                if (ss.startsWith("#") && ss.length > 3) {
                    val t = hashtagService?.getByTag(ss)
                    if (t == null)
                        result.add(HashTag(tag = ss.trim(), lastMentionedDate = LocalDateTime.now(), registrationDate = LocalDateTime.now()))
                    else {
                        t.lastMentionedDate = LocalDateTime.now()
                        result.add(t)
                    }
                }

            }
        }

        return result
    }

}