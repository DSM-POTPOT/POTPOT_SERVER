package com.example.potpot.domain.auth.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object UmExistVerifyCodeException : POTPOTException(ErrorCode.UNEXIST_VERIFY_CODE)
