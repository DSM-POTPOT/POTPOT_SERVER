package com.example.potpot.domain.feed.service

import com.example.potpot.domain.feed.domain.CommentRepository
import com.example.potpot.domain.user.exception.UserMismatchException
import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteCommentService(
    private val userFacade: UserFacade,
    private val commentRepository: CommentRepository
) {
    @Transactional
    fun execute(id: Long){
        val user = userFacade.getCurrentUser()
        val comment = commentRepository.findById(id).orElseThrow()

        if(user.id != comment.user.id) throw UserMismatchException

        commentRepository.delete(comment)
    }
}