package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.FeedRepository
import com.example.potpot.domain.feed.presentation.dto.response.CommentResponse
import com.example.potpot.domain.feed.presentation.dto.response.FeedDetailsResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional


@Service
class GetFeedDetailsService(
    private val feedRepository: FeedRepository
) {
    @Transactional
    fun execute(feedId: Long): Optional<FeedDetailsResponse> {
        val feed = feedRepository.findById(feedId)
        return feed.map {
            FeedDetailsResponse(
                feedId = it.id,
                title = it.title,
                content = it.content,
                date = it.format(it.date),
                category = it.category,
                image = it.image,
                isOK = it.isOK,
                userName = it.user.name,
                comment = it.comments.map { comment ->
                    CommentResponse(
                        comment = comment.comment,
                    )
                }
            )
        }
    }
}