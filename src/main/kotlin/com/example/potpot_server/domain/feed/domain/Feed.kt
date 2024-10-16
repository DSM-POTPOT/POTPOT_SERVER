package com.example.potpot_server.domain.feed.domain

import com.example.potpot_server.domain.feed.enum.Category
import com.example.potpot_server.domain.user.domain.User
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
import java.time.LocalDate

@Entity(name = "tbl_feed")
data class Feed(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    val title: String = "",

    @Column(nullable = false)
    val content: String = "",

    @Column(nullable = false)
    val date: LocalDate = LocalDate.now(),

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val category: Category = Category.ETC,

    val image: String? = null,

    @Column(name = "is_ok", nullable = false)
    val isOK: Boolean = true,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User = User()
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
}
