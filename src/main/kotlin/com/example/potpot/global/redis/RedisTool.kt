package com.example.potpot.global.redis

import com.example.potpot.domain.auth.presentation.dto.EmailVerifyNumberMatchRequest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Component
@Transactional
class RedisTool(private val redisTemplate: RedisTemplate<String, String>) {

    fun setValues(key: String, data: String) {
        val values: ValueOperations<String, String> = redisTemplate.opsForValue()
        values.set(key, data)
    }

    fun setValues(key: String, data: String, duration: Duration) {
        val values: ValueOperations<String, String> = redisTemplate.opsForValue()
        values.set(key, data, duration)
    }

    @Transactional(readOnly = true)
    fun getValues(request: EmailVerifyNumberMatchRequest): String? {
        val valueOperations = redisTemplate.opsForValue()
        return valueOperations[request.email]
    }

    fun checkExistsValue(value: String): Boolean {
        return value != "false"
    }
}
