package com.example.potpot_server.domain.user.presentation

//import com.example.potpot_server.domain.auth.presentation.EmailVerifyNumberRequest
import com.example.potpot_server.domain.user.presentation.dto.request.SignInRequest
import com.example.potpot_server.domain.user.presentation.dto.request.SignUpRequest
//import com.example.potpot_server.domain.auth.service.EmailTool
import com.example.potpot_server.domain.user.presentation.dto.response.TokenResponse
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
    private val signInService: SignInService,
//    private val emailTool: EmailTool
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(@RequestBody @Valid request: SignUpRequest) {
        signUpService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): TokenResponse {
        return signInService.execute(request)
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/email")
//    fun verifySend(@RequestBody @Valid request: EmailVerifyNumberRequest){
//        emailTool.sendEmail(request)
//    }
}