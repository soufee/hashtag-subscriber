package ci.ashamaz.hashtagsubscriber.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        val messageId: Long = 0L,
        val title: String = "",
        val date: LocalDateTime = LocalDateTime.now(),
        val text: String = "",
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "channel_id")
        val channel: Channel? = null

) {
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var tags: Set<HashTag> = mutableSetOf()

    fun getTagsFromText(): Set<HashTag> {
        val result = mutableSetOf<HashTag>()
        if (text.contains('#')) {
            var words = text.split(" ")
            for (s in words) {
                if (s.startsWith('#'))  result.add(HashTag(tag = s.trim()))
            }
        }

        return result
    }
}