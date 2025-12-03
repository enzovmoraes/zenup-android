package com.example.zenup.data.repository

import com.example.zenup.data.network.ApiClient
import com.example.zenup.data.api.ZenUpApi
import org.example.model.*
import java.io.IOException

class AuthRepository(
    private val apiService: ZenUpApi = ApiClient.api
) {

    // FUNÇÃO DE LOGIN
    suspend fun login(request: LoginRequest): LoginResponse {
        val response = apiService.login(request)

        if (response.isSuccessful) {
            // Retorna o corpo da resposta, que contém o token
            val loginResponse = response.body() ?: throw IOException("Resposta de login vazia.")

            // Salva o token no ApiClient para requisições futuras
            ApiClient.authToken = loginResponse.token

            return loginResponse
        } else {
            // Trata erros (credenciais inválidas, etc.)
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            val errorMessage = "Falha no login. Erro HTTP ${response.code()}: $errorBody"
            throw IOException(errorMessage)
        }
    }

    // FUNÇÃO DE CHAT
    suspend fun chat(request: ChatRequest): ChatResponse {
        val response = apiService.chat(request)

        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de chat vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            throw IOException("Erro no chat. HTTP ${response.code()}: $errorBody")
        }
    }

    // FUNÇÃO DE RESUMO
    suspend fun resumo(idUsuario: Long): ResumoResponse {
        val response = apiService.resumo(idUsuario)

        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de resumo vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            throw IOException("Erro ao buscar resumo. HTTP ${response.code()}: $errorBody")
        }
    }
}