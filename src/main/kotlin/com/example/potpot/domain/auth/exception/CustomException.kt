package com.example.potpot.domain.auth.exception

import org.springframework.http.HttpStatus

class CustomException(
    val httpStatus: HttpStatus,
    override val message: String
) : RuntimeException(message)
