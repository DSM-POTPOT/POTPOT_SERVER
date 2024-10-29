package com.example.potpot.domain.feed.domain

import com.example.potpot.domain.comment.domain.Comment
import com.example.potpot.domain.feed.enum.Category
import com.example.potpot.domain.user.domain.User
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(name = "tbl_feed")
data class Feed(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false, length = 50)
    var title: String = "",

    @Column(nullable = false, length = 500)
    var content: String = "",

    @Column(nullable = false)
    var date: LocalDate = LocalDate.now(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var category: Category = Category.ETC,

    val image: String? = null,

    @Column(name = "is_ok", nullable = false)
    val isOK: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = User(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "feed", cascade = [CascadeType.ALL])
    val comments: List<Comment> = emptyList()
) {
    constructor() : this(
        id = 0,
        title = "",
        content = "",
        date = LocalDate.now(),
        category = Category.ETC,
        image = null,
        user = User()
    )

    fun modifyFeed(title: String, content: String, date: LocalDate, category: Category) {
        this.title = title
        this.content = content
        this.date = date
        this.category = category
    }

    fun format(date: LocalDate) = date.format(DateTimeFormatter.ofPattern("yy.MM.dd"))!!
}
