package com.example.potpot_server.domain.user.presentation.dto.response

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
)
