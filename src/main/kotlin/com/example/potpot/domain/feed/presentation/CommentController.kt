package com.example.potpot.domain.feed.presentation

import com.example.potpot.domain.feed.presentation.dto.request.CommentRequest
import com.example.potpot.domain.feed.service.CreateCommentService
import com.example.potpot.domain.feed.service.DeleteCommentService
import com.example.potpot.domain.feed.service.ModifyCommentService
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
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
    private val modifyCommentService: ModifyCommentService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}")
    fun createComment(
        @PathVariable("feed-id") id: Long,
        @RequestBody @Valid request: CommentRequest
    ) = createCommentService.execute(id, request)

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{comment-id}")
    fun deleteComment(@PathVariable("comment-id") id: Long) = deleteCommentService.execute(id)

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{comment-id}")
    fun updateComment(@PathVariable("comment-id") id: Long, @RequestBody @Valid request: CommentRequest) =
        modifyCommentService.execute(id, request)
}