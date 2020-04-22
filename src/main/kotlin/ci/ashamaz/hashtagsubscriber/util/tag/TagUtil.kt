package ci.ashamaz.hashtagsubscriber.util.tag

import ci.ashamaz.hashtagsubscriber.model.HashTag
import ci.ashamaz.hashtagsubscriber.service.HashTagService
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
            var words = text.split(" ", "\n", "\\.", "\\,")
            for (s in words) {
                if (s.startsWith('#')) {
                    val ss = s.trim().replace(".", "").replace(",", "")
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