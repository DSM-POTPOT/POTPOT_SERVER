package com.example.potpot_server.domain.user.service

import com.example.potpot_server.domain.user.facade.UserFacade
import com.example.potpot_server.domain.user.presentation.dto.response.UserInfoResponse
import org.springframework.stereotype.Service

@Service
class UserInfoService(
    private val userFacade: UserFacade
) {
    fun execute(): UserInfoResponse {
        val user = userFacade.getCurrentUser()
        return UserInfoResponse(user.schoolNumber, user.name, user.email, user.imageUrl)
    }
}
