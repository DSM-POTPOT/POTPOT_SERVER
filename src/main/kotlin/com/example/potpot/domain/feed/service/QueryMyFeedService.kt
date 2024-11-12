package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.presentation.dto.response.FeedResponse
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryMyFeedService(
    private val feedRepository: FeedRepository,
    private val userFacade: UserFacade
) {

    @Transactional(readOnly = true)
    fun execute(): List<FeedResponse> {
        val user = userFacade.getCurrentUser()
        val feeds = feedRepository.findAllByUserId(user.id)

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
        }.sortedByDescending { it.feedId }
    }
}
