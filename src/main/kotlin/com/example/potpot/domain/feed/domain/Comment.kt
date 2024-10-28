package com.example.potpot.domain.feed.domain

import com.example.potpot.domain.user.domain.User
import jakarta.persistence.*
import org.joda.time.LocalDate

@Entity(name = "tbl_comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    var content: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user :User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    val feed: Feed
) {
    constructor(): this(
        id = 0,
        content = "",
        user = User(),
        feed = Feed()
    )

    fun modifyComment(content: String){
        this.content = content
    }
}