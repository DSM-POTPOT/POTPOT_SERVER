package com.example.potpot_server.domain.auth.service

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@Component
@Transactional
class RedisTool(private val redisTemplate: RedisTemplate<String, Any>) {

    fun setValues(key: String, data: String) {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        values.set(key, data)
    }

    fun setValues(key: String, data: String, duration: Duration) {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        values.set(key, data, duration)
    }

    @Transactional(readOnly = true)
    fun getValues(key: String): String {
        val values: ValueOperations<String, Any> = redisTemplate.opsForValue()
        return values.get(key)?.toString() ?: "false"
    }

    fun checkExistsValue(value: String): Boolean {
        return value != "false"
    }
}