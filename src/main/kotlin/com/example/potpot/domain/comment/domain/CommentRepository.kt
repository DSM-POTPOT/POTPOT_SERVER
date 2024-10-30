package com.example.potpot.domain.comment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long>{
}