package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.Comment
import com.example.potpot.domain.feed.domain.CommentRepository
import com.example.potpot.domain.feed.facade.FeedFacade
import com.example.potpot.domain.feed.presentation.dto.request.CommentRequest
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
    fun execute(id: Long, request: CommentRequest){
        val user = userFacade.getCurrentUser()
        val feed = feedFacade.getCurrentFeed(id)
            .orElseThrow{NoSuchElementException("Feed with id $id not found.")}

        commentRepository.save(
            Comment(
                id,
                comment = request.comment,
                user = user,
                feed = feed
            )
        )
    }
}