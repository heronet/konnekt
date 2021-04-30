package com.example.konnekt.model

import com.squareup.moshi.Json

data class User(
    @Json(name = "_id") val _id: String,
    val email: String?,
    val name: String?,
    val username: String
)