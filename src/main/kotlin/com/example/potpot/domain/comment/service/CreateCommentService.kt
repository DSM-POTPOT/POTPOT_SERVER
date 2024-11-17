package com.example.potpot.domain.comment.service

import com.example.potpot.domain.comment.domain.Comment
import com.example.potpot.domain.comment.domain.CommentRepository
import com.example.potpot.domain.feed.exception.FeedNotFoundException
import com.example.potpot.domain.feed.facade.FeedFacade
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateCommentService(
    private val userFacade: UserFacade,
    private val feedFacade: FeedFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(feedId: Long, request: String) {
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getCurrentFeed(feedId) ?: throw FeedNotFoundException
        commentRepository.save(
            Comment(
                comment = request,
                user = user,
                feed = feed
            )
        )
    }
}
