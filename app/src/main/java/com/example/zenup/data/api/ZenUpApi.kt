package com.example.zenup.data.api

import retrofit2.Responseimport retrofit2.http.Body
import retrofit2.http.GETimport retrofit2.http.POST
import retrofit2.http.Path

interface ZenUpApi {
    @POST("api/login")
    suspend fun login(@Body req: LoginRequest): Response<LoginResponse>

    @POST("api/chat")
    suspend fun chat(@Body req: ChatRequest): Response<ChatResponse>

    @GET("api/resumo/{id_usuario}")
    suspend fun resumo(@Path("id_usuario") id: Long): Response<ResumoResponse>
}