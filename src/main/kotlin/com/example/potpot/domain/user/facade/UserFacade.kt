package com.example.potpot.domain.user.facade

import com.example.potpot.domain.user.domain.User
import com.example.potpot.domain.user.domain.repository.UserRepository
import com.example.potpot.domain.user.exception.UserNotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {
    fun getCurrentUser(): User {
        val schoolNumber: String = SecurityContextHolder.getContext().authentication.name
        return getBySchoolNumber(schoolNumber)
    }

    fun checkAccountIdExist(schoolNumber: String): Boolean {
        return userRepository.existsBySchoolNumber(schoolNumber)
    }

    fun getBySchoolNumber(schoolNumber: String): User {
        return userRepository.findBySchoolNumber(schoolNumber) ?: throw UserNotFoundException
    }
}
