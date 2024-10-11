package com.example.potpot_server.domain.feed.presentation

import com.example.potpot_server.domain.feed.presentation.dto.request.CreateRequest
import com.example.potpot_server.domain.feed.service.CreateFeedService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/feed")
class FeedController(
    private val createFeedService: CreateFeedService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(
        @RequestPart(name = "image")
        file: MultipartFile?,
        @RequestPart(name = "request") @Valid request: CreateRequest
    ) = createFeedService.execute(file, request)
}
