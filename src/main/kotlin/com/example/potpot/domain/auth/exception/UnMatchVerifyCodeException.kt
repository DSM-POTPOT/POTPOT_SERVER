package com.example.potpot.domain.auth.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object UnMatchVerifyCodeException : POTPOTException(ErrorCode.UNMATCHED_VERIFY_CODE)
