package com.example.potpot.domain.user.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
