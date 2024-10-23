package com.example.potpot.domain.user.domain.repository

import com.example.potpot.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {
    fun findBySchoolNumber(schoolNumber: String): User?
    fun existsBySchoolNumber(schoolNumber: String): Boolean
}
