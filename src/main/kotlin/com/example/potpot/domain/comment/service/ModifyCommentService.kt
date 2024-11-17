package com.example.potpot.domain.comment.service

import com.example.potpot.domain.comment.facade.CommentFacade
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyCommentService(
    private val userFacade: UserFacade,
    private val commentFacade: CommentFacade
) {
    @Transactional
    fun execute(commentId: Long, request: String) {
        val user = userFacade.getCurrentUser()
        val comment = commentFacade.getCurrentComment(commentId)

        if (!user.equals(comment.user)) throw UserMismatchException

        comment.modifyComment(request)
    }
}
