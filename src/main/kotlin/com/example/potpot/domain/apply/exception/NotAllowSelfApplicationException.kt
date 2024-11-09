package com.example.potpot.domain.apply.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object NotAllowSelfApplicationException : POTPOTException(
    ErrorCode.NOT_ALLOW_SELF_APPLICATION
)
