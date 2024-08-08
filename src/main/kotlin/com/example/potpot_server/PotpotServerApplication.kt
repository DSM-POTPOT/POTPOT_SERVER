package com.example.potpot_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PotpotServerApplication

fun main(args: Array<String>) {
    runApplication<PotpotServerApplication>(*args)
}
