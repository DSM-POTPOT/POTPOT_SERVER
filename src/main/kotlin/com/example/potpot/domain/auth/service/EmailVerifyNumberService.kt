package com.example.potpot.domain.auth.service

import com.example.potpot.domain.auth.domain.repository.EmailVerifyRepository
import com.example.potpot.domain.auth.exception.CustomException
import com.example.potpot.domain.auth.presentation.dto.EmailVerifyNumberRequest
import com.example.potpot.global.redis.RedisTool
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
    private val redisTool: RedisTool,
    private val javaMailSender: JavaMailSender,
    private val emailVerifyRepository: EmailVerifyRepository
) {
    private val log = LoggerFactory.getLogger(EmailVerifyNumberService::class.java)

    @Value("\${spring.mail.auth-code-expiration-millis}")
    private val authCodeExpirationMillis: Long = 0

    fun makeRandomNumber(): String {
        val random = Random()
        val checkNum = random.nextInt(888888) + 111111
        return checkNum.toString()
    }
    val random = makeRandomNumber()

    private fun createEmailForm(request: EmailVerifyNumberRequest): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setTo(request.email)
            setSubject("유저 이메일 인증 번호")
            setText("POTPOT 이메일 인증번호 입니다 : $random 타인에게 공유하지 마세요")
        }
    }

    // 인증번호 전송 서비스
    fun sendEmail(request: EmailVerifyNumberRequest) {
        val emailForm = createEmailForm(request)
        try {
            javaMailSender.send(emailForm)
            log.info("이메일 발송 성공")
        } catch (e: Exception) {
            log.error("이메일 발송 오류", e)
        }
    }

    fun sendCodeToEmail(emailVerifyNumberRequest: EmailVerifyNumberRequest) {
        // 이메일 중복 검사
        if (emailVerifyRepository.existsByEmail(emailVerifyNumberRequest.email)) {
            throw CustomException(HttpStatus.CONFLICT, "중복 된 이메일 입니다.")
        }
        // 인증코드 생성, 저장 및 이메일 전송
        // 이메일 인증 요청 시 인증 번호 Redis에 저장
        redisTool.setValues(
            emailVerifyNumberRequest.email,
            random,
            Duration.ofMillis(authCodeExpirationMillis)
        )
        sendEmail(emailVerifyNumberRequest)
    }
}
