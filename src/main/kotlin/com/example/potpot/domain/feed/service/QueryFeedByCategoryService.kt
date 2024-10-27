package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.enum.Category
import com.example.potpot.domain.feed.presentation.dto.response.FeedResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryFeedByCategoryService(
    private val feedRepository: FeedRepository
) {

    @Transactional(readOnly = true)
    fun execute(category: Category): List<FeedResponse> {
        val feeds = feedRepository.findAllByCategory(category)
        return feeds.map {
            FeedResponse(
                id = it.id,
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
