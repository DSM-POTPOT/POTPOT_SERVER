package com.example.potpot_server.domain.auth.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object UnMatchVerifyCodeException : POTPOTException(ErrorCode.UNMATCHED_VERIFY_CODE)
