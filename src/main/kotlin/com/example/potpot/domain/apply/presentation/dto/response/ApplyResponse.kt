package com.example.potpot.domain.apply.presentation.dto.response

import com.example.potpot.domain.comment.domain.Comment

data class ApplyResponse(
    val schoolNumber: String,
    val name: String,
    val feedId: Long,
    val comment: List<Comment>,
    val applyId: Long,
    val isOK: Boolean
)
