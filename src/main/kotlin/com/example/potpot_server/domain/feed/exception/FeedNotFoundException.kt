package com.example.potpot_server.domain.feed.exception

import com.example.potpot_server.global.error.exception.ErrorCode
import com.example.potpot_server.global.error.exception.POTPOTException

object FeedNotFoundException : POTPOTException(ErrorCode.FEED_NOT_FOUND)
