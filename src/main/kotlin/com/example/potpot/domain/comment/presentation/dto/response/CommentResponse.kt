package com.example.potpot.domain.comment.presentation.dto.response

data class CommentResponse(
    val feedId: Long,

    val userId: Long,

    val comment: String
)
