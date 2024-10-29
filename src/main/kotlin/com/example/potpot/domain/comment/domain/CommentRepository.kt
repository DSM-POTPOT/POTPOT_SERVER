package com.example.potpot.domain.comment.domain

import com.example.potpot.domain.comment.domain.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>{
}