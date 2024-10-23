package com.example.potpot.domain.feed.exception

import com.example.potpot.global.error.exception.ErrorCode
import com.example.potpot.global.error.exception.POTPOTException

object FeedNotFoundException : POTPOTException(ErrorCode.FEED_NOT_FOUND)
