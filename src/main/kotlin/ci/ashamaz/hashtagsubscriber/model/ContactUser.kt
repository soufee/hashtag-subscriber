package ci.ashamaz.hashtagsubscriber.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "contactuser")
data class ContactUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val user_id: Long = 0,
        @Column(name = "first_name")
        val firstName: String? = "",
        @Column(name = "last_name")
        val lastName: String? = "",
        @Column(name = "isbot")
        val isBot: Boolean = false,
        @Column(name = "user_name")
        val userName: String? = "",
        @Column(name = "language_code")
        val languageCode: String? = "RU",
        @Column(name = "chat_id", nullable = false, unique = true)
        val chatId: Long = 0,
        @Column(name = "admin")
        var admin: Boolean = false,
        @Column(name = "registration_date")
        var registrationDate: LocalDateTime? = null


) {
        @ManyToMany(fetch = FetchType.EAGER)
        val subscriptions: Set<HashTag> = mutableSetOf()

}

