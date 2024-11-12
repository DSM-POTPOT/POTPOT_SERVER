package com.example.potpot.global.config.security

import com.example.potpot.global.config.filter.FilterConfig
import com.example.potpot.global.security.jwt.TokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
    private val tokenProvider: TokenProvider
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors().configurationSource { request ->
                CorsConfiguration().apply {
                    addAllowedOrigin("*")
                    addAllowedMethod("*")
                    addAllowedHeader("*")
                }
            }.and()
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    // user
                    .requestMatchers(HttpMethod.POST, "/user/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/signin").permitAll()
                    .requestMatchers(HttpMethod.POST, "/user/email").permitAll()
                    .requestMatchers(HttpMethod.GET, "/user/email").permitAll()
                    .requestMatchers(HttpMethod.GET, "/user/users").authenticated()
                    .requestMatchers(HttpMethod.POST, "/user/file").authenticated()
                    // feed
                    .requestMatchers(HttpMethod.POST, "/feed").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/feed").authenticated()
                    .requestMatchers(HttpMethod.GET, "/feed/{feed-id}").authenticated()
                    .requestMatchers(HttpMethod.GET, "/feed/query/all").authenticated()
                    .requestMatchers(HttpMethod.GET, "/feed/query/my").authenticated()
                    // comment
                    .requestMatchers(HttpMethod.POST, "/comment/{feed-id}").authenticated()
                    .requestMatchers(HttpMethod.DELETE, "/comment/{comment-id}").authenticated()
                    .requestMatchers(HttpMethod.PATCH, "/comment/{comment-id}").authenticated()
            }
        http
            .apply(FilterConfig(objectMapper, tokenProvider))
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
