package com.example.potpot_server.domain.user.presentation.dto.request

data class SignUpRequest(
    val schoolNumber: String,

    val name: String,

    val password: String,

    val mail: String,

    val imageUrl: String
)