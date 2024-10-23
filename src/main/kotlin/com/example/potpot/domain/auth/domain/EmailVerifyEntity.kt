package com.example.potpot.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "tbl_email_verify_number", timeToLive = 60 * 60 * 2)
class EmailVerifyEntity(
    @Id
    val email: String,

    val verifyNumber: String
)
