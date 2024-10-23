package com.example.potpot.domain.user.service

import com.example.potpot.domain.user.exception.IncorrectPasswordException
import com.example.potpot.domain.user.facade.UserFacade
import com.example.potpot.domain.user.presentation.dto.request.SignInRequest
import com.example.potpot.domain.user.presentation.dto.response.TokenResponse
import com.example.potpot.global.security.jwt.TokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {
    @Transactional
    fun execute(request: SignInRequest): TokenResponse {
        val user = userFacade.getBySchoolNumber(request.schoolNumber)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw IncorrectPasswordException
        }
        return tokenProvider.generateToken(request.schoolNumber)
    }
}
