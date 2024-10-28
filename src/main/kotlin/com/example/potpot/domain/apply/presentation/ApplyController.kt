package com.example.potpot.domain.apply.presentation

import com.example.potpot.domain.apply.presentation.dto.request.ApplyRequest
import com.example.potpot.domain.apply.sevice.ApplyService
import com.example.potpot.domain.apply.sevice.DeleteApplyService
import com.example.potpot.domain.apply.sevice.QueryMyApplyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    private val queryMyApplyService: QueryMyApplyService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody request: ApplyRequest) = applyService.execute(request)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun delete(@RequestParam(name = "id")id: Long) = deleteApplyService.execute(id)

    @GetMapping("/query/my")
    fun queryMyApply() = queryMyApplyService.execute()
}
