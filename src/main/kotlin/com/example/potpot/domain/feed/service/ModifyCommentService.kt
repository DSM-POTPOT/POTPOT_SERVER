package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.Comment
import com.example.potpot.domain.feed.domain.CommentRepository
import com.example.potpot.domain.feed.facade.CommentFacade
import com.example.potpot.domain.feed.facade.FeedFacade
import com.example.potpot.domain.feed.presentation.dto.request.CommentRequest
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyCommentService(
    private val userFacade: UserFacade,
    private val commentFacade: CommentFacade,
) {
    @Transactional
    fun execute(commentId:Long, request: CommentRequest){
        val user = userFacade.getCurrentUser()
        val comment = commentFacade.getCurrentComment(commentId)

        if(!user.equals(comment.user)) throw UserMismatchException

        comment.modifyComment(request.comment)
    }
}