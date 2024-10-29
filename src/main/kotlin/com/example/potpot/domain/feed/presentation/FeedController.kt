package com.example.potpot.domain.feed.presentation

import com.example.potpot.domain.feed.enum.Category
import com.example.potpot.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot.domain.feed.service.*
import com.example.potpot.domain.feed.service.GetFeedDetailsService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
    private val deleteFeedService: DeleteFeedService,
    private val modifyFeedService: ModifyFeedService,
    private val queryAllFeedService: QueryAllFeedService,
    private val queryFeedByCategoryService: QueryFeedByCategoryService,
    private val getFeedDetailsService: GetFeedDetailsService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(
        @RequestPart(name = "image")
        file: MultipartFile?,
        @RequestPart(name = "request") @Valid request: FeedRequest
    ) = createFeedService.execute(file, request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun delete(@RequestParam(name = "id") id: Long) =
        deleteFeedService.execute(id)

    @PatchMapping
    fun update(
        @RequestParam(name = "id")
        id: Long,
        @Valid
        @RequestBody
        request: FeedRequest
    ) = modifyFeedService.execute(id, request)

    @GetMapping("/query")
    fun getFeed(@RequestParam("feed_id") feedId: Long) =
        getFeedDetailsService.execute(feedId)

    @GetMapping("/query/all")
    fun queryAll() = queryAllFeedService.execute()

    @GetMapping("/query/category")
    fun queryByCategory(@RequestParam(name = "category") category: Category) =
        queryFeedByCategoryService.execute(category)
}
