package com.example.potpot.domain.auth.presentation.dto

data class EmailVerifyNumberMatchRequest(
    val email: String,

    val verifyNumber: String
)
