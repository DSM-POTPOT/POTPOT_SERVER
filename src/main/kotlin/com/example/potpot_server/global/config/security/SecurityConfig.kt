package com.example.potpot_server.global.config.security

import com.example.potpot_server.global.config.filter.FilterConfig
import com.example.potpot_server.global.security.jwt.TokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val tokenProvider: TokenProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .cors(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // auth
                    .requestMatchers(HttpMethod.POST, "/users/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/signin").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/email").permitAll()
                    .requestMatchers(HttpMethod.GET, "/users/email").permitAll()
            }
        http
            .apply(FilterConfig(objectMapper, tokenProvider))
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}