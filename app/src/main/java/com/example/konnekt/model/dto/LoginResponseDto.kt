package com.example.konnekt.model.dto

data class LoginResponseDto(
    val token: String,
    val userId: String,
    val username: String,
    val message: String?
)