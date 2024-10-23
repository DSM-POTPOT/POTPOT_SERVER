package com.example.potpot.global.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object TokenExpiredException : POTPOTException(ErrorCode.TOKEN_EXPIRED)
