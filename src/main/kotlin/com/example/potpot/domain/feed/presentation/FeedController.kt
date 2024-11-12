package com.example.potpot.domain.feed.presentation

import com.example.potpot.domain.feed.enum.Category
import com.example.potpot.domain.feed.presentation.dto.request.FeedRequest
import com.example.potpot.domain.feed.service.CreateFeedService
import com.example.potpot.domain.feed.service.DeleteFeedService
import com.example.potpot.domain.feed.service.GetFeedDetailsService
import com.example.potpot.domain.feed.service.ModifyFeedService
import com.example.potpot.domain.feed.service.QueryAllFeedService
import com.example.potpot.domain.feed.service.QueryFeedByCategoryService
import com.example.potpot.domain.feed.service.QueryMyFeedService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
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
    private val deleteFeedService: DeleteFeedService,
    private val modifyFeedService: ModifyFeedService,
    private val queryAllFeedService: QueryAllFeedService,
    private val queryFeedByCategoryService: QueryFeedByCategoryService,
    private val getFeedDetailsService: GetFeedDetailsService,
    private val queryMyFeedService: QueryMyFeedService
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
        @RequestPart(name = "request")
        request: FeedRequest,
        @RequestPart(name = "file")
        file: MultipartFile?
    ) = modifyFeedService.execute(id, request, file)

    @GetMapping("{feed-id}")
    fun getFeed(@PathVariable("feed-id") feedId: Long) =
        getFeedDetailsService.execute(feedId)

    @GetMapping("/query/all")
    fun queryAll() = queryAllFeedService.execute()

    @GetMapping("/query/category")
    fun queryByCategory(@RequestParam(name = "category") category: Category) =
        queryFeedByCategoryService.execute(category)

    @GetMapping("/query/my")
    fun queryMyFeedService() = queryMyFeedService.execute()
}
