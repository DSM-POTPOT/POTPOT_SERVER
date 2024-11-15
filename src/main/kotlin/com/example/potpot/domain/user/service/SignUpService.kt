package com.example.potpot.domain.user.service

import com.example.potpot.domain.user.domain.User
import com.example.potpot.domain.user.domain.repository.UserRepository
import com.example.potpot.domain.user.presentation.dto.request.SignUpRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class SignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    @Value("\${cloud.aws.stack.default.image.address}")
    private lateinit var defaultImageAddress: String

    @Transactional
    fun execute(request: SignUpRequest) {
        if (userRepository.existsBySchoolNumber(request.schoolNumber)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 ${request.schoolNumber} 학번 입니다.")
        }

        val imageUrl = if (request.imageUrl.isNullOrEmpty()) {
            defaultImageAddress
        } else {
            request.imageUrl
        }

        val newUser = User(
            schoolNumber = request.schoolNumber,
            name = request.name,
            password = passwordEncoder.encode(request.password),
            email = request.email,
            imageUrl = imageUrl
        )
        userRepository.save(newUser)
    }

    fun checkSchoolNumber(schoolNumber: String): Boolean {
        return userRepository.existsBySchoolNumber(schoolNumber)
    }
}
