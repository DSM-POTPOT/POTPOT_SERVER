package com.example.potpot_server.domain.auth.domain.repository

import com.example.potpot_server.domain.auth.domain.EmailVerifyEntity
import com.example.potpot_server.domain.auth.presentation.dto.EmailVerifyNumberMatchRequest
import com.example.potpot_server.domain.auth.presentation.dto.EmailVerifyNumberRequest
import com.example.potpot_server.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmailVerifyRepository : JpaRepository<EmailVerifyEntity, String>{
    fun findByEmail(email: String): EmailVerifyEntity?
    fun existsByEmail(email: String): Boolean
}