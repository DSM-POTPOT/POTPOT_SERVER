package com.example.potpot_server.global.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object TokenInvalidException : POTPOTException(ErrorCode.TOKEN_EXPIRED)