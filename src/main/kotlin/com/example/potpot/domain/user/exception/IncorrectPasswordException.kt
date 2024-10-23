package com.example.potpot.domain.user.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object IncorrectPasswordException : POTPOTException(ErrorCode.INCORRECT_PASSWORD)
