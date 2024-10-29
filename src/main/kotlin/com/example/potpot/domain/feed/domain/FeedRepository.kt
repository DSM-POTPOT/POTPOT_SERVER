package com.example.potpot.domain.feed.domain

import com.example.potpot.domain.feed.enum.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface FeedRepository : JpaRepository<Feed, Long> {
    fun findAllByCategory(category: Category): List<Feed>

}