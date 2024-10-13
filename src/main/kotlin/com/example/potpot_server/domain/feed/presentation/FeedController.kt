package com.example.potpot_server.domain.feed.presentation

import com.example.potpot_server.domain.feed.presentation.dto.request.CreateRequest
import com.example.potpot_server.domain.feed.service.CreateFeedService
import com.example.potpot_server.domain.feed.service.DeleteFeedService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/feed")
class FeedController(
    private val createFeedService: CreateFeedService,
    private val deleteFeedService: DeleteFeedService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(
        @RequestPart(name = "image")
        file: MultipartFile?,
        @RequestPart(name = "request") @Valid request: CreateRequest
    ) = createFeedService.execute(file, request)

    @ResponseStatus
    @DeleteMapping
    fun delete(@RequestParam(name = "id") id: Long) =
        deleteFeedService.execute(id)
}
