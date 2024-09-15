package com.example.potpot_server.domain.auth.presentation.dto

data class EmailVerifyNumberMatchRequest(
    val email: String,

    val verifyNumber: String
) {
}