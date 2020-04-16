package ci.ashamaz.hashtagsubscriber.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "hashtag")
data class HashTag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val tag_id: Long = 0L,
        @Column(name = "tag", nullable = false)
        val tag: String = "",
        @Column(name = "registration_date", nullable = true)
        var registrationDate: LocalDateTime? = null,
        @Column(name = "last_mentioned_date", nullable = true)
        var lastMentionedDate: LocalDateTime? = null,
        @Column(name = "last_subscribed_date", nullable = true)
        var lastSubscribedDate: LocalDateTime? = null
) {
    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.EAGER)
    val subscribers: Set<ContactUser> = mutableSetOf()

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    var messages: Set<Message> = mutableSetOf()
}