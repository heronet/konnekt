package com.example.konnekt.network

import com.example.konnekt.model.Message
import com.example.konnekt.model.dto.LoginDto
import com.example.konnekt.model.dto.LoginResponseDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.*

private const val BASE_URL = "https://guarded-caverns-50373.herokuapp.com/api/"
//private const val BASE_URL = "https://guarded-caverns-50373.herokuapp.com/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface HttpApiService {
    @POST("users/login")
    suspend fun login(@Body loginDto: LoginDto): LoginResponseDto

    @GET("messages/inbox")
    suspend fun getInbox(@Header("Authorization") token: String): List<Message>

    @GET("messages/{user}")
    suspend fun getConversation(@Header("Authorization") token: String, @Path("user") user: String): List<Message>
}

object HttpApi {
    val retrofitService: HttpApiService by lazy {

        retrofit.create(HttpApiService::class.java)
    }
}