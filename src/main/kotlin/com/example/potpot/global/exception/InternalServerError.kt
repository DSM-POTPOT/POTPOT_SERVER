package com.example.potpot.global.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object InternalServerError : POTPOTException(ErrorCode.INTERNAL_SERVER_ERROR)
