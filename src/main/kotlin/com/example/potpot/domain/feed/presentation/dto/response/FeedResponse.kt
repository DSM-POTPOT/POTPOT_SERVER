package com.example.potpot.domain.feed.presentation.dto.response

import com.example.potpot.domain.feed.enum.Category

data class FeedResponse(
    val feedId: Long,
    val title: String,
    val content: String,
    val date: String,
    val category: Category,
    val image: String?,
    val isOK: Boolean,
    val userName: String
)
