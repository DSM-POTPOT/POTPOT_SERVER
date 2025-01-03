package com.example.potpot.domain.user.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "tbl_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 5, nullable = false)
    val id: Long = 0,

    @Column(name = "school_number", length = 5, nullable = false, unique = true)
    val schoolNumber: String = "",

    @Column(length = 10, nullable = false)
    var name: String = "",

    @Column(length = 80, nullable = false)
    val password: String = "",

    @Column(length = 55, nullable = false)
    val email: String = "",

    @Column(name = "image_url", nullable = false)
    val imageUrl: String? = null
) {

    constructor() : this(
        0,
        "",
        "",
        "",
        "",
        null
    )
    fun updateUserInfo(name: String) {
        this.name = name
    }
}
