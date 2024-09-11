package com.example.potpot_server.domain.user.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object UserNotFoundException : POTPOTException(ErrorCode.USER_NOT_FOUND)