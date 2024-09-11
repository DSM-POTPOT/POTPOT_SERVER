package com.example.potpot_server.domain.user.domain.repository

import com.example.potpot_server.domain.user.domain.RefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : JpaRepository<RefreshToken, String>