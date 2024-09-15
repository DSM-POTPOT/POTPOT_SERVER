package com.example.potpot_server.domain.auth.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object AlreadyExistEmailException : POTPOTException(ErrorCode.ALREADY_EXIST_EMAIL)