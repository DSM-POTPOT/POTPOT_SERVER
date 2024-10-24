package com.example.potpot.global.exception

import org.springframework.http.HttpStatus

interface BaseExceptionType {
    fun ErrorCode(): Int
    fun HttpStatus(): HttpStatus
    fun getErrorMessage(): String
}