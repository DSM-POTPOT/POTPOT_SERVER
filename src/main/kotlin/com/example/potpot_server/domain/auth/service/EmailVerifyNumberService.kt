package com.example.potpot_server.domain.auth.service

import com.example.potpot_server.domain.auth.exception.CustomException
import com.example.potpot_server.domain.auth.presentation.EmailVerifyNumberRequest
import com.example.potpot_server.domain.user.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration
import java.util.*

// 인증번호 생성 및 검증 담당 서비스
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class EmailVerifyNumberService(
    private val userRepository: UserRepository,
    private val emailTool: EmailTool,
    private val redisTool: RedisTool
) {
    private val AUTH_CODE_PREFIX = "AuthCode"

    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    fun sendCodeToEmail(emailVerifyNumberRequest: EmailVerifyNumberRequest) {
        // 이메일 중복 검사
        if (userRepository.existsByMail(emailVerifyNumberRequest.email)) {
            throw CustomException(HttpStatus.CONFLICT, "중복 된 이메일 입니다.")
        }

        val authCode = makeRandomNumber()

        // 인증코드 생성, 저장 및 이메일 전송
        // 이메일 인증 요청 시 인증 번호 Redis에 저장
        redisTool.setValues(
            AUTH_CODE_PREFIX + emailVerifyNumberRequest.email,
            authCode,
            Duration.ofMillis(authCodeExpirationMillis)
        )
        emailTool.sendEmail(emailVerifyNumberRequest)
    }

    fun verifyCode(email: String, authCode: String): Boolean {
        val redisAuthCode = redisTool.getValues(AUTH_CODE_PREFIX + email)

        return redisTool.checkExistsValue(redisAuthCode) && redisAuthCode == authCode
    }

    fun makeRandomNumber(): String {
        val random = Random()
        val checkNum = random.nextInt(888888) + 111111

        return checkNum.toString()
    }
}