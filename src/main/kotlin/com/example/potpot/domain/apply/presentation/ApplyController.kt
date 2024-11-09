package com.example.potpot.domain.apply.presentation

import com.example.potpot.domain.apply.presentation.dto.request.ApplyRequest
import com.example.potpot.domain.apply.presentation.dto.request.ChangeStatusRequest
import com.example.potpot.domain.apply.service.ApplyService
import com.example.potpot.domain.apply.service.ChangeIsOKService
import com.example.potpot.domain.apply.service.DeleteApplyService
import com.example.potpot.domain.apply.service.QueryFeedApplyService
import com.example.potpot.domain.apply.service.QueryMyApplyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apply")
class ApplyController(
    private val applyService: ApplyService,
    private val deleteApplyService: DeleteApplyService,
    private val queryMyApplyService: QueryMyApplyService,
    private val queryFeedApplyService: QueryFeedApplyService,
    private val changeIsOKService: ChangeIsOKService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody request: ApplyRequest) = applyService.execute(request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun delete(@RequestParam(name = "feed_id")id: Long) = deleteApplyService.execute(id)

    @PatchMapping
    fun update(@RequestBody request: ChangeStatusRequest) = changeIsOKService.execute(
        request
    )

    @GetMapping("/query/my")
    fun queryMyApply() = queryMyApplyService.execute()

    @GetMapping("/query/feed")
    fun queryByFeed(@RequestParam(name = "feed_id")id: Long) = queryFeedApplyService.execute(id)
}
