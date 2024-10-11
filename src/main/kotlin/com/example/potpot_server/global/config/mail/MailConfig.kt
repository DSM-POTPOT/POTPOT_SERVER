package com.example.potpot_server.global.config.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*

@Configuration
class MailConfig() {
    @Value("\${spring.mail.host}")
    private val host: String? = null

    @Value("\${spring.mail.port}")
    private val mailPort = 0

    @Value("\${spring.mail.username}")
    private val mailUsername: String? = null

    @Value("\${spring.mail.password}")
    private val mailPassword: String? = null

    @Bean
    fun javaMailSender(): JavaMailSender {
        val javaMailSender = JavaMailSenderImpl().apply {
            host = host
            username = mailUsername
            password = mailPassword
            port = mailPort
            defaultEncoding = "UTF-8"
            javaMailProperties = getMailProperties()
        }
        return javaMailSender
    }
    private fun getMailProperties(): Properties {
        return Properties().apply {
            this["mail.smtp.host"] = host
            this["mail.smtp.port"] = mailPort
            this["mail.transport.protocol"] = "smtp"
            this["mail.smtp.auth"] = "true"
            this["mail.smtp.starttls.enable"] = "true"
            this["mail.debug"] = "true"
        }
    }

//    fun getMailProperties(): Properties {
//        return Properties().apply {
//            setProperty("mail.smtp.auth", "true")
//            setProperty("mail.smtp.host", host)
//            setProperty("mail.smtp.port", mailPort.toString())
//            setProperty("mail.smtp.starttls.enable", "true")
//        }
//    }
}
