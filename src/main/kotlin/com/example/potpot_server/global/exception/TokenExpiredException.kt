package com.example.potpot_server.global.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object TokenExpiredException : POTPOTException(ErrorCode.TOKEN_EXPIRED)