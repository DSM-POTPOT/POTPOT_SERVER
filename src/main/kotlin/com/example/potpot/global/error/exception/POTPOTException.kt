package com.example.potpot.global.error.exception

abstract class POTPOTException(
    val errorCode: ErrorCode
) : RuntimeException() {
    val status: Int
        get() = errorCode.status
    override val message: String
        get() = errorCode.message
}
