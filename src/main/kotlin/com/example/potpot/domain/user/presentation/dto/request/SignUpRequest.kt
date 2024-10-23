package com.example.potpot.domain.user.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

data class SignUpRequest(
    @JsonProperty("school_number")
    val schoolNumber: String,

    val name: String,

    val password: String,

    val email: String,

    @JsonProperty("image_url")
    val imageUrl: String
)
