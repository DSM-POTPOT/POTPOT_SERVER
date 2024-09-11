package com.example.potpot_server

import com.example.potpot_server.global.security.jwt.TokenProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(TokenProperties::class)
@SpringBootApplication
class PotpotServerApplication

fun main(args: Array<String>) {
    runApplication<PotpotServerApplication>(*args)
}
