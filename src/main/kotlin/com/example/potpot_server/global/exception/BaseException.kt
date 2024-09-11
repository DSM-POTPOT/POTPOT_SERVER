package com.example.potpot_server.global.exception

abstract class BaseException : RuntimeException() {
    abstract fun getExceptionType(): BaseExceptionType
}