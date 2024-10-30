package com.example.potpot.domain.comment.domain

import com.example.potpot.domain.feed.domain.Feed
import com.example.potpot.domain.user.domain.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity(name = "tbl_comment")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, length = 100)
    var comment: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    val feed: Feed
) {
    constructor() : this(
        id = 0,
        comment = "",
        user = User(),
        feed = Feed()
    )

    fun modifyComment(newComment: String): Comment {
        return this.copy(comment = newComment)
    }
}
