package ci.ashamaz.hashtagsubscriber.model

import javax.persistence.*

@Entity
@Table(name = "hashtag")
data class HashTag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val tag_id: Long = 0L,
        @Column(name = "tag", nullable = false)
        val tag: String = "",
        @ManyToMany
        @JoinTable(name = "contactuser_subscribers",
                joinColumns = [JoinColumn(name = "tag_id")],
                inverseJoinColumns = [JoinColumn(name = "user_id")]
        )
        val subscribers: Set<ContactUser> = mutableSetOf()

)