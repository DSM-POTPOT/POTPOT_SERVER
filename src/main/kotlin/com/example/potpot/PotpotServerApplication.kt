package com.example.potpot

import com.example.potpot.global.security.jwt.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(TokenProperties::class)
@SpringBootApplication
class PotpotServerApplication

fun main(args: Array<String>) {
    runApplication<PotpotServerApplication>(*args)
}
