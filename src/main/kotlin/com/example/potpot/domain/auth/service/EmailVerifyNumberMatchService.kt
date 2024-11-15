package com.example.potpot.domain.auth.service

import com.example.potpot.domain.auth.exception.UmExistVerifyCodeException
import com.example.potpot.domain.auth.exception.UnMatchVerifyCodeException
import com.example.potpot.global.redis.RedisTool
import org.springframework.stereotype.Service

@Service
class EmailVerifyNumberMatchService(
    private val redisTool: RedisTool
) {
//    // 인증번호 매치
    fun verifiedCode(email: String, verifyNumber: String) {
        val redisAuthCode = redisTool.getValues(email, verifyNumber) ?: throw UmExistVerifyCodeException
        if (redisAuthCode != verifyNumber) {
            throw UnMatchVerifyCodeException
        }
    }
}
