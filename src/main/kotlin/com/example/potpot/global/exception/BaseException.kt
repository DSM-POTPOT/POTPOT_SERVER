package com.example.potpot.global.exception

abstract class BaseException : RuntimeException() {
    abstract fun getExceptionType(): BaseExceptionType
}
