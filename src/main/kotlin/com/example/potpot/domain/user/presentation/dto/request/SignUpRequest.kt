package com.example.potpot.domain.user.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class SignUpRequest(

    @JsonProperty("school_number")
    @NotNull
    val schoolNumber: String,

    @JsonProperty("name")
    @NotNull
    val name: String,

    @JsonProperty("password")
    @NotNull
    val password: String,

    @JsonProperty
    @NotNull
    val email: String,

    @JsonProperty("image_url")
    @NotNull
    val imageUrl: String
)
