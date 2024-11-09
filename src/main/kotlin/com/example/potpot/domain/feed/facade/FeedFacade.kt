package com.example.potpot.domain.feed.facade

import com.example.potpot.domain.feed.domain.Feed
import com.example.potpot.domain.feed.domain.FeedRepository
import org.springframework.stereotype.Component

@Component
class FeedFacade(
    private val feedRepository: FeedRepository
) {
    fun getCurrentFeed(id: Long): Feed? {
        return feedRepository.findById(id)
    }
}
