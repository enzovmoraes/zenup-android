// AuthApiService.kt (Completo)
package com.example.zenup.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    // 1. ENDPOINT DE CADASTRO
    @POST("cadastro")
    suspend fun cadastrarUsuario(
        @Body request: CadastroRequest
    ): Response<CadastroResponse>

    // 2. ENDPOINT DE LOGIN
    @POST("login")
    suspend fun loginUsuario(
        @Body request: LoginRequest
    ): Response<CadastroResponse> // Retorna a mesma resposta que o cadastro (ID, Token)

    // 3. ENDPOINT DE REGISTRO DIÁRIO
    @POST("registro")
    suspend fun registrarDiario(
        @Body request: RegistroDiarioRequest
    ): Response<RegistroDiarioResponse>
}