package com.example.potpot.domain.user.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object UserMismatchException : POTPOTException(ErrorCode.USER_MISMATCH_EXCEPTION)
