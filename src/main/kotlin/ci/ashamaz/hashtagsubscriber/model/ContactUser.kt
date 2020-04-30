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

        @ManyToMany(fetch = FetchType.EAGER)
        val excludedChannels: Set<Channel> = mutableSetOf()

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as ContactUser

                if (userName != other.userName) return false
                if (chatId != other.chatId) return false

                return true
        }

        override fun hashCode(): Int {
                var result = userName?.hashCode() ?: 0
                result = 31 * result + chatId.hashCode()
                return result
        }


}

