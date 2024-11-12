package com.example.potpot.domain.comment.service

import com.example.potpot.domain.comment.presentation.dto.response.CommentResponse
import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.feed.presentation.dto.response.FeedDetailsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetFeedDetailsService(
    private val feedRepository: FeedRepository
) {
    @Transactional(readOnly = true)
    fun execute(feedId: Long): FeedDetailsResponse {
        val feed = feedRepository.findById(feedId) ?: throw FeedNotFoundException
        return FeedDetailsResponse(
            feedId = feed.id,
            title = feed.title,
            content = feed.content,
            date = feed.format(feed.date),
            category = feed.category,
            image = feed.image,
            isOK = feed.isOK,
            userName = feed.user.name,
            commentList = feed.comments.map { comment ->
                CommentResponse(
                    feedId = comment.id,
                    userId = comment.user.id,
                    comment = comment.comment
                )
            }
        )
    }
}
