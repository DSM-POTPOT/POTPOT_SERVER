package com.example.potpot_server.domain.user.presentation

import com.example.potpot_server.domain.user.presentation.dto.request.SignInRequest
import com.example.potpot_server.domain.user.presentation.dto.request.SignUpRequest
import com.example.potpot_server.domain.user.service.SignInService
import com.example.potpot_server.domain.user.service.SignUpService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest) {
        signUpService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest) {
        signInService.execute(request)
    }
}