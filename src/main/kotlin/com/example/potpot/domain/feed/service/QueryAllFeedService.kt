package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.presentation.dto.response.FeedResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryAllFeedService(
    private val feedRepository: FeedRepository
) {

    @Transactional(readOnly = true)
    fun execute(): List<FeedResponse> {
        val feeds = feedRepository.findAll()
        return feeds.map {
            FeedResponse(
                feedId = it.id,
                title = it.title,
                content = it.content,
                date = it.format(it.date),
                category = it.category,
                image = it.image,
                isOK = it.isOK,
                userName = it.user.name
            )
        }
    }
}
