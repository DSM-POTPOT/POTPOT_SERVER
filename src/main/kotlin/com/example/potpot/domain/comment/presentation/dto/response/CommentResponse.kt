package com.example.potpot.domain.comment.presentation.dto.response

data class CommentResponse(
    val feedId: Long,

    val schoolNumber: String,

    val name: String,

    val commentId: Long,

    val comment: String
)
