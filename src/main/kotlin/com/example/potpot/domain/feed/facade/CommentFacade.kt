package com.example.potpot.domain.feed.facade

import com.example.potpot.domain.feed.domain.Comment
import com.example.potpot.domain.feed.domain.CommentRepository
import com.example.potpot.domain.feed.exception.CommentNotFoundException
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class CommentFacade(
    private val commentRepository: CommentRepository
) {
    fun getCurrentComment(id: Long): Comment {
        return commentRepository.findById(id).orElseThrow() { CommentNotFoundException }
    }
}