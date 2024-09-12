package com.example.potpot_server.global.config.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfig(
) {
    @Value("\${spring.mail.host}")
    private val host: String? = null

    @Value("\${spring.mail.port}")
    private val port = 0

    @Value("\${spring.mail.username}")
    private val username: String? = null

    @Value("\${spring.mail.password}")
    private val password: String? = null

    @Value("\${spring.mail.properties.mail.smtp.auth}")
    private val auth = false

    @Bean
    fun javaMailSender(): JavaMailSender{
        val javaMailSender = JavaMailSenderImpl().apply {
            host = host
            username = username
            password = password
            port = port
            defaultEncoding = "UTF-8"
            javaMailProperties = getMailProperties()
        }
        return javaMailSender
    }

    @Bean
    fun getMailProperties(): Properties {
        return Properties().apply {
            setProperty("mail.smtp.auth", "true")
            setProperty("mail.smtp.host", host)
            setProperty("mail.smtp.port", port.toString())
            setProperty("mail.smtp.starttls.enable", "true")
        }
    }
}