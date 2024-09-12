package com.example.potpot_server.domain.auth.presentation

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class EmailVerifyNumberRequest(
    @JsonProperty("email")
    @NotNull
    val email: String,
)