package com.example.potpot_server.domain.user.domain.repository

import com.example.potpot_server.domain.user.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, String>