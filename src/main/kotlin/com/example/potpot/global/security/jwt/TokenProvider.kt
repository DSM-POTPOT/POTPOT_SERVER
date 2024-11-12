package com.example.potpot.global.security.jwt

import com.example.potpot.domain.user.domain.RefreshToken
import com.example.potpot.domain.user.domain.repository.RefreshTokenRepository
import com.example.potpot.domain.user.presentation.dto.response.TokenResponse
import com.example.potpot.global.exception.TokenExpiredException
import com.example.potpot.global.exception.TokenInvalidException
import com.example.potpot.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class TokenProvider(
    private val tokenProperties: TokenProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    val secretKey: SecretKey = Keys.hmacShaKeyFor(tokenProperties.secretKey.toByteArray())
    fun generateToken(schoolNumber: String): TokenResponse {
        val accessToken: String = generateAccessToken(schoolNumber)
        val refreshToken: String = generateRefreshToken(schoolNumber)
        return TokenResponse(accessToken, refreshToken)
    }

    fun generateAccessToken(schoolNumber: String): String {
        return createToken(schoolNumber, "access", tokenProperties.accessExp)
    }

    fun generateRefreshToken(schoolNumber: String): String {
        val refreshToken = createToken(schoolNumber, "refresh", tokenProperties.refreshExp)
        refreshTokenRepository.save(RefreshToken(schoolNumber, refreshToken))
        return refreshToken
    }

    private fun createToken(schoolNumber: String, type: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(schoolNumber)
            .claim("school_number", schoolNumber)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .compact()
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getSchoolNumber(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getSchoolNumber(token: String): String {
        val claims = getClaims(token)
        return claims.subject ?: throw IllegalArgumentException("Subject cannot be null")
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenInvalidException
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return parseToken(bearerToken)
    }

    private fun parseToken(bearerToken: String?): String? {
        return if (!bearerToken.isNullOrEmpty() && bearerToken.startsWith("Bearer ")) {
            bearerToken.removePrefix("Bearer ")
        } else {
            null
        }
    }

//    fun validateToken(token: String): Boolean {
//        return try {
//            val claims = Jwts.parser().setSigningKey(tokenProperties.secretKey).parseClaimsJws(token).body
//            true
//        } catch (e: Exception) {
//            false
//        }
//    }
}
