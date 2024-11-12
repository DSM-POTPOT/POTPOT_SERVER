package com.example.potpot.domain.comment.presentation

import com.example.potpot.domain.comment.presentation.dto.request.CommentRequest
import com.example.potpot.domain.comment.service.CreateCommentService
import com.example.potpot.domain.comment.service.DeleteCommentService
import com.example.potpot.domain.comment.service.GetFeedDetailsService
import com.example.potpot.domain.comment.service.ModifyCommentService
import com.example.potpot.domain.feed.presentation.dto.response.FeedDetailsResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comment")
class CommentController(
    private val createCommentService: CreateCommentService,
    private val deleteCommentService: DeleteCommentService,
    private val modifyCommentService: ModifyCommentService,
    private val getFeedDetailsService: GetFeedDetailsService

) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}")
    fun createComment(
        @PathVariable("feed-id") feedId: Long,
        @RequestBody @Valid
        request: CommentRequest
    ) = createCommentService.execute(feedId, request)

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{comment-id}")
    fun deleteComment(
        @PathVariable("comment-id") commentId: Long
    ) = deleteCommentService.execute(commentId)

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{comment-id}")
    fun updateComment(
        @PathVariable("comment-id") commentId: Long,
        @RequestBody @Valid
        request: CommentRequest
    ) = modifyCommentService.execute(commentId, request)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{feed-id}")
    fun getDetailsFeed(@PathVariable("feed-id") feedId: Long): FeedDetailsResponse =
        getFeedDetailsService.execute(feedId)
}
