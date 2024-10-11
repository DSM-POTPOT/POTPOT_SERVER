package com.example.potpot_server.domain.user.domain

import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "tbl_refresh_token", timeToLive = 60 * 60 * 2)
class RefreshToken(
    @Id
    val schoolNumber: String,

    @Indexed
    @get:NotBlank
    var refreshToken: String
)
