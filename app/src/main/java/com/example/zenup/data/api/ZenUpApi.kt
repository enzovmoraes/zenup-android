package com.example.zenup.data.api

import org.example.model.ChatRequest
import org.example.model.ChatResponse
import org.example.model.LoginRequest
import org.example.model.LoginResponse
import org.example.model.RegistroDiarioRequest
import org.example.model.RegistroDiarioResponse  // ← Adicione explicitamente
import org.example.model.ResumoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ZenUpApi {
    @POST("api/login")
    suspend fun login(@Body req: LoginRequest): Response<LoginResponse>

    @POST("api/chat")
    suspend fun chat(@Body req: ChatRequest): Response<ChatResponse>

    @GET("api/resumo/{id_usuario}")
    suspend fun resumo(@Path("id_usuario") id: Long): Response<ResumoResponse>

    @POST("api/registro-diario")
    suspend fun registrarDiario(@Body req: RegistroDiarioRequest): Response<RegistroDiarioResponse>
}