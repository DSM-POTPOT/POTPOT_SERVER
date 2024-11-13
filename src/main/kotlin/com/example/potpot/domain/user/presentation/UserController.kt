package com.example.potpot.domain.user.presentation

import com.example.potpot.domain.auth.presentation.dto.EmailVerifyNumberMatchRequest
import com.example.potpot.domain.auth.presentation.dto.EmailVerifyNumberRequest
import com.example.potpot.domain.auth.service.EmailVerifyNumberMatchService
import com.example.potpot.domain.auth.service.EmailVerifyNumberService
import com.example.potpot.domain.user.presentation.dto.request.SignInRequest
import com.example.potpot.domain.user.presentation.dto.request.SignUpRequest
import com.example.potpot.domain.user.presentation.dto.request.UserInfoUpdateRequest
import com.example.potpot.domain.user.presentation.dto.response.ProfileImageUploadResponse
import com.example.potpot.domain.user.presentation.dto.response.TokenResponse
import com.example.potpot.domain.user.presentation.dto.response.UserInfoResponse
import com.example.potpot.domain.user.service.ProfileImageUploadService
import com.example.potpot.domain.user.service.SignInService
import com.example.potpot.domain.user.service.SignUpService
import com.example.potpot.domain.user.service.UserInfoService
import com.example.potpot.domain.user.service.UserInfoUpdateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/user")
@RestController
class UserController(
    private val signUpService: SignUpService,
    private val signInService: SignInService,
    private val emailVerifyNumberService: EmailVerifyNumberService,
    private val emailVerifyNumberMatchService: EmailVerifyNumberMatchService,
    private val userInfoService: UserInfoService,
    private val profileImageUploadService: ProfileImageUploadService,
    private val userInfoUpdateService: UserInfoUpdateService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun signup(
        @RequestBody @Valid
        request: SignUpRequest
    ) = signUpService.execute(request)

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    fun signIn(
        @RequestBody @Valid
        request: SignInRequest
    ): TokenResponse = signInService.execute(request)

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/email")
    fun verifySend(
        @RequestParam("email") request: EmailVerifyNumberRequest
    ) = emailVerifyNumberService.sendCodeToEmail(request)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/email")
    fun verifyMatch(
        @RequestBody @Valid
        request: EmailVerifyNumberMatchRequest
    ) = emailVerifyNumberMatchService.verifiedCode(request)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    fun getUserInfo(): UserInfoResponse =
        userInfoService.execute()

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/users")
    fun updateUserInfo(
        @RequestBody @Valid
        request: UserInfoUpdateRequest
    ) = userInfoUpdateService.execute(request)

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/file")
    fun imageUpload(
        @RequestParam("file") multipartFile: MultipartFile
    ): ProfileImageUploadResponse =
        profileImageUploadService.execute(multipartFile)
}
