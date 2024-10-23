package com.example.potpot.global.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object TokenInvalidException : POTPOTException(ErrorCode.TOKEN_EXPIRED)
