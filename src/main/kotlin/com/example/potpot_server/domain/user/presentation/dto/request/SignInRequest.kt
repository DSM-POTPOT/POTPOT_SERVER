package com.example.potpot_server.domain.user.presentation.dto.request


data class SignInRequest(

    val schoolNumber: String,

    val password: String
)