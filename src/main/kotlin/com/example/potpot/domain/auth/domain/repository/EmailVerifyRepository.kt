package com.example.potpot.domain.auth.domain.repository

import com.example.potpot.domain.auth.domain.EmailVerifyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmailVerifyRepository : JpaRepository<EmailVerifyEntity, String> {
    fun findByEmail(email: String): EmailVerifyEntity?
    fun existsByEmail(email: String): Boolean
}
