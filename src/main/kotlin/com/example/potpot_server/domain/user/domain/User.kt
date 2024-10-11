package com.example.potpot_server.domain.user.domain

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
    val id: Int,

    @Column(name = "school_number", length = 5, nullable = false)
    val schoolNumber: String,

    @Column(length = 10, nullable = false)
    val name: String,

    @Column(length = 80, nullable = false)
    val password: String,

    @Column(length = 55, nullable = false)
    val email: String,

    @Column(name = "image_url", nullable = false)
    val imageUrl: String
) {

    public constructor() : this(0, "", "", "", "", "")
    constructor(id: Int, schoolNumber: String, name: String, password: String, email: String) : this(
        id,
        "",
        "",
        "",
        "",
        ""
    )
}
