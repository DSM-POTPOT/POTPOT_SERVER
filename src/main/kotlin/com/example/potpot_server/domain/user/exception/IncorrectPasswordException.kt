package com.example.potpot_server.domain.user.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object IncorrectPasswordException : POTPOTException(ErrorCode.INCORRECT_PASSWORD)