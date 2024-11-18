package com.example.potpot.domain.apply.presentation.dto.response

import com.example.potpot.domain.feed.enum.Category
import java.time.LocalDate

data class QueryByFeedResponse(
    val applyId: Long,
    val feedId: Long,
    val title: String,
    val content: String,
    val date: LocalDate,
    val category: Category,
    val image: String?,
    val is_ok: Boolean,
    val name: String
)
