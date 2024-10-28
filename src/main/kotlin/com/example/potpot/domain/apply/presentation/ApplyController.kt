package com.example.potpot.domain.apply.presentation

import com.example.potpot.domain.apply.presentation.dto.request.ApplyRequest
import com.example.potpot.domain.apply.sevice.ApplyService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/apply")
class ApplyController(
    private val applyService: ApplyService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody request: ApplyRequest) = applyService.execute(request)
}
