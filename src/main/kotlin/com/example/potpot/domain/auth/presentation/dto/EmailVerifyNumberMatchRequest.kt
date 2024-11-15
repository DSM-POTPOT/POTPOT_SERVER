package com.example.potpot.domain.auth.presentation.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class EmailVerifyNumberMatchRequest(
    @JsonProperty("email")
    @NotNull
    val email: String,

    @JsonProperty("email")
    @NotNull
    val verifyNumber: String
)
