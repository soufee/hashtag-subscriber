package ci.ashamaz.hashtagsubscriber.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "channel")

data class Channel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val channel_id: Long = 0L,
        @Column(name = "chat_id", nullable = true)
        val chatId: Long? = null,
        @Column(name = "channel_name")
        val channelName: String = "",
        @Column(name = "registration_date", nullable = true)
        val registrationDate: LocalDateTime? = LocalDateTime.now(),
        @Column(name = "last_message_date", nullable = true)
        var lastMessageDate: LocalDateTime? = LocalDateTime.now(),
        @Column(name = "warnings")
        var warnings: Int = 0,
        @Column(name = "banned")
        var banned: Boolean = false,
        @Column(name = "link")
        var link: String? = "",
        @Column(name = "weblink")
        var weblink: String? = ""
) {
    @OneToMany(mappedBy = "channel", fetch = FetchType.EAGER)
    val messages: Set<Message> = mutableSetOf()

    @ManyToMany(mappedBy = "excludedChannels", fetch = FetchType.EAGER)
    val exclusions: Set<ContactUser> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Channel

        if (channelName != other.channelName) return false
        if (link != other.link) return false
        if (weblink != other.weblink) return false

        return true
    }

    override fun hashCode(): Int {
        var result = channelName.hashCode()
        result = 31 * result + (link?.hashCode() ?: 0)
        result = 31 * result + (weblink?.hashCode() ?: 0)
        return result
    }


}