package com.example.potpot.domain.apply.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object ApplyNotFoundException : POTPOTException(
    ErrorCode.APPLY_NOT_FOUND
)
