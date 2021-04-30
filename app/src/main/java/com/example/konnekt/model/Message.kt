package com.example.konnekt.model

import com.squareup.moshi.Json

data class Message(
    @Json(name = "_id") val id : String,
    val text : String,
    val sender : User,
    val recipient : User,
    val createdAt : String?,
    val updatedAt : String?,
    val displayName: String?
)