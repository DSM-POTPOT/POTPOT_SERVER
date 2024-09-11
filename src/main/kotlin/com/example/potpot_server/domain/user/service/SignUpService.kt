package com.example.potpot_server.domain.user.service

import com.example.potpot_server.domain.user.domain.User
import com.example.potpot_server.domain.user.domain.repository.UserRepository
import com.example.potpot_server.domain.user.presentation.dto.request.SignUpRequest
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
    @Transactional
    fun execute(request:SignUpRequest){
        if(!checkSchoolNumber(request.schoolNumber)){
            val newUser = User(
                request.schoolNumber,
                request.name,
                passwordEncoder.encode(request.password),
                request.mail,
                request.imageUrl
            )
            userRepository.save(newUser)
        } else{
            throw ResponseStatusException(HttpStatus.CONFLICT, "이미 존재하는 ${request.schoolNumber} 학번 입니다.")
        }
    }

    fun checkSchoolNumber(schoolNumber: String): Boolean{
        return userRepository.existsBySchoolNumber(schoolNumber)
    }
}