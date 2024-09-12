package com.example.potpot_server.domain.user.domain

import jakarta.persistence.*

@Entity(name = "tbl_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_number", length = 5, nullable = false)
    val schoolNumber: String,

    @Column(length = 10, nullable = false)
    val name: String,

    @Column(length = 80, nullable = false)
    val password: String,

    @Column(length = 55, nullable = false)
    val mail: String,

    @Column(name = "image_url")
    val imageUrl: String
){
    constructor() : this( "","", "", "", "")

}