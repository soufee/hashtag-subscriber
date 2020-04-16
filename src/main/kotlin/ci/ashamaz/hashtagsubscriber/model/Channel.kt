package ci.ashamaz.hashtagsubscriber.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="channel")

data class Channel (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val channel_id: Long = 0L,
        @Column(name="chat_id", unique = true, nullable = false)
        val chatId: Long = 0L,
        @Column(name = "channel_name")
        val channelName: String = "",
        @Column(name = "registration_date", nullable = true)
        val registrationDate: LocalDateTime?= LocalDateTime.now(),
        @Column(name="last_message_date", nullable = true)
        var lastMessageDate: LocalDateTime?= LocalDateTime.now(),
        @Column(name="warnings")
        var warnings: Int = 0,
        @Column(name = "banned")
        var banned: Boolean = false
)
{
        @OneToMany(mappedBy = "channel", fetch = FetchType.EAGER)
        val messages: Set<Message> = mutableSetOf()
}