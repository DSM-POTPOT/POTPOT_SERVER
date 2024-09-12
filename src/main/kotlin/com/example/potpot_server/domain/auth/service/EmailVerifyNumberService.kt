package com.example.potpot_server.domain.auth.service

import com.example.potpot_server.domain.auth.exception.CustomException
import com.example.potpot_server.domain.auth.presentation.EmailVerifyNumberRequest
import com.example.potpot_server.domain.user.domain.repository.UserRepository
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
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
    private val redisTool: RedisTool,
    private val javaMailSender: JavaMailSender
) {
    private val log = LoggerFactory.getLogger(EmailVerifyNumberService::class.java)

    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    fun makeRandomNumber(): String {
        val random = Random()
        val checkNum = random.nextInt(888888) + 111111
        return checkNum.toString()
    }

    fun sendCodeToEmail(emailVerifyNumberRequest: EmailVerifyNumberRequest) {
        // 이메일 중복 검사
        if (userRepository.existsByMail(emailVerifyNumberRequest.email)) {
            throw CustomException(HttpStatus.CONFLICT, "중복 된 이메일 입니다.")
        }

        // 인증코드 생성, 저장 및 이메일 전송
        // 이메일 인증 요청 시 인증 번호 Redis에 저장
        redisTool.setValues(
            emailVerifyNumberRequest.email, makeRandomNumber(),
            Duration.ofMillis(authCodeExpirationMillis)
        )
        sendEmail(emailVerifyNumberRequest)
    }

    fun verifyCode(email: String, authCode: String): Boolean {
        val redisAuthCode = redisTool.getValues(authCode + email)
        return redisTool.checkExistsValue(redisAuthCode) && redisAuthCode == authCode
    }


    // 인증번호 전송 서비스
    fun sendEmail(emailVerifyNumberRequest: EmailVerifyNumberRequest) {
        val emailForm = createEmailForm(emailVerifyNumberRequest)
        try {
            javaMailSender.send(emailForm)
            log.info("이메일 발송 성공")
        } catch (e: Exception) {
            log.error("이메일 발송 오류", e)
        }
    }

    private fun createEmailForm(emailVerifyNumberRequest: EmailVerifyNumberRequest): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setTo(emailVerifyNumberRequest.email)
            setSubject("유저 이메일 인증 번호")
            setText("POTPOT 이메일 인증번호 입니다 : ${makeRandomNumber()} 타인에게 공유하지 마세요")
        }
    }

}