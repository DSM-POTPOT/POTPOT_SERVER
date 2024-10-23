package com.example.potpot.global.security.auth

import com.example.potpot.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {
    override fun loadUserByUsername(schoolNumber: String): UserDetails {
        val user = userFacade.getBySchoolNumber(schoolNumber)
        return AuthDetails(user)
    }
}
