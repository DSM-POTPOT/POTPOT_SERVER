package com.example.potpot.domain.apply.domain

import com.example.potpot.domain.feed.domain.Feed
import com.example.potpot.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface ApplyRepository : JpaRepository<Apply, Long> {
    fun findAllByUser(user: User): List<Apply>

    fun existsByUserAndFeed(user: User, feed: Feed): Boolean
}
