package com.example.potpot.domain.feed.facade

import com.example.potpot.domain.feed.domain.Feed
import com.example.potpot.domain.feed.domain.FeedRepository
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class FeedFacade(
    private val feedRepository: FeedRepository
) {
    fun getCurrentFeed(id: Long): Optional<Feed> {
        return feedRepository.findById(id)
    }
}
