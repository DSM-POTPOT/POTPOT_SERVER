package com.example.potpot.domain.feed.presentation.dto.request

import com.example.potpot.domain.feed.enum.Category
import jakarta.validation.constraints.Size
import java.beans.ConstructorProperties
import java.time.LocalDate

data class FeedRequest(
    @Size(min = 1, max = 30)
    val title: String,
    @Size(min = 1, max = 300)
    val content: String,
    val date: LocalDate,
    val category: Category
)
