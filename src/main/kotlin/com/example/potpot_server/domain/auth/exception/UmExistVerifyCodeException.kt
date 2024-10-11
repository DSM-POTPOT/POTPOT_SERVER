package com.example.potpot_server.domain.auth.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object UmExistVerifyCodeException : POTPOTException(ErrorCode.UNEXIST_VERIFY_CODE)
