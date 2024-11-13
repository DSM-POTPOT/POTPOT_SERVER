package com.example.potpot.domain.user.service

import com.example.potpot.domain.user.domain.repository.UserRepository
import com.example.potpot.domain.user.facade.UserFacade
import com.example.potpot.domain.user.presentation.dto.request.UserInfoUpdateRequest
import org.springframework.stereotype.Service

@Service
class UserInfoUpdateService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    fun execute(request: UserInfoUpdateRequest) {
        val user = userFacade.getCurrentUser()
        user.updateUserInfo(request.name)

        userRepository.save(user)
    }
}
