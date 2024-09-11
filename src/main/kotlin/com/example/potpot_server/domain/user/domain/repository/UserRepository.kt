package com.example.potpot_server.domain.user.domain.repository

import com.example.potpot_server.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String>{
    fun findBySchoolNumber(schoolNumber: String): User?
    fun existsBySchoolNumber(schoolNumber: String): Boolean
}
