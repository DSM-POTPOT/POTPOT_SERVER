package com.example.potpot.domain.feed.domain

import com.example.potpot.domain.feed.enum.Category
import org.springframework.data.repository.Repository

interface FeedRepository : Repository<Feed, Long> {
    fun save(feed: Feed)

    fun findById(id: Long): Feed?

    fun findAllByCategory(category: Category): List<Feed>

    fun findAll(): List<Feed>

    fun findAllByUserId(userId: Int): List<Feed>

    fun deleteById(id: Long)
}
