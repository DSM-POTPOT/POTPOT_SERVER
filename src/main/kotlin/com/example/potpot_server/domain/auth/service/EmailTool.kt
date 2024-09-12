package com.example.potpot_server.domain.auth.service

import com.example.potpot_server.domain.auth.presentation.EmailVerifyNumberRequest
import org.slf4j.LoggerFactory
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


// 인증번호 전송 서비스
@Service
class EmailTool(
    private val javaMailSender: JavaMailSender
) {

    private val log = LoggerFactory.getLogger(EmailTool::class.java)

    fun sendEmail(emailVerifyNumberRequest: EmailVerifyNumberRequest){
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
            setText("POTPOT 이메일 인증번호 입니다. 타인에게 공유하지 마세요")

        }
    }
}