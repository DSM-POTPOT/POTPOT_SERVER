package com.example.potpot_server.domain.auth.presentation

data class EmailVerifyNumberRequest(
    val email: String,

    val title: String,

    val text : String
)