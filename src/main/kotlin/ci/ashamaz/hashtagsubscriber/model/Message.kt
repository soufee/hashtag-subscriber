package ci.ashamaz.hashtagsubscriber.model

import org.telegram.telegrambots.meta.api.objects.PhotoSize
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0L,
        val messageId: Long = 0L,
        val title: String? = "",
        val date: LocalDateTime = LocalDateTime.now(),
        @Column(length = 5000)
        val text: String? = "",
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "channel_id")
        val channel: Channel? = null,
        @Column(length = 5000)
        val caption: String? = null,
        @Column(name = "link")
        var link: String? = "",
        @Column(name = "weblink")
        var weblink: String? = ""
) {
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH])
    var tags: Set<HashTag> = mutableSetOf()
}