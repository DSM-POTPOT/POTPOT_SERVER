package com.example.potpot.domain.feed.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CommentRepository : JpaRepository<Comment, Long>{
}