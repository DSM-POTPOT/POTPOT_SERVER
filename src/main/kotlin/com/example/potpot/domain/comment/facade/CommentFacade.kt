package com.example.potpot.domain.comment.facade

import com.example.potpot.domain.comment.domain.Comment
import com.example.potpot.domain.comment.domain.CommentRepository
import com.example.potpot.domain.comment.exception.CommentNotFoundException
import org.springframework.stereotype.Component

@Component
class CommentFacade(
    private val commentRepository: CommentRepository
) {
    fun getCurrentComment(id: Long): Comment {
        return commentRepository.findById(id).orElseThrow() { CommentNotFoundException }
    }
}
