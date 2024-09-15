package com.example.potpot_server.domain.auth.service

import com.example.potpot_server.domain.auth.domain.repository.EmailVerifyRepository
import com.example.potpot_server.domain.auth.exception.UmExistVerifyCodeException
import com.example.potpot_server.domain.auth.exception.UnMatchVerifyCodeException
import com.example.potpot_server.domain.auth.presentation.dto.EmailVerifyNumberMatchRequest
import com.example.potpot_server.global.redis.RedisTool
import org.springframework.stereotype.Service

@Service
class EmailVerifyNumberMatchService(
    private val redisTool: RedisTool,
    private val emailVerifyRepository: EmailVerifyRepository
) {

//    // 인증번호 매치
    fun verifiedCode(request: EmailVerifyNumberMatchRequest){
        val redisAuthCode = redisTool.getValues(request) ?: throw UmExistVerifyCodeException
        if(redisAuthCode != request.verifyNumber){
            throw UnMatchVerifyCodeException
        }
        println("Verification successful for email: ${request.email}")
    }
}