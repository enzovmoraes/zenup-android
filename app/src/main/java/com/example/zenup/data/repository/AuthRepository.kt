// AuthRepository.kt (Atualizado)
package com.example.zenup.data.repository

import com.example.zenup.data.api.AuthApiService
import com.example.zenup.data.api.RetrofitClient
import java.io.IOException

class AuthRepository(
    private val apiService: AuthApiService = RetrofitClient.authApiService
) {
    // FUNÇÃO DE CADASTRO (Já tínhamos)
    suspend fun cadastrar(request: CadastroRequest): CadastroResponse {
        // ... (Lógica de cadastro)
        val response = apiService.cadastrarUsuario(request)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de cadastro vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            throw IOException("Erro HTTP ${response.code()}: $errorBody")
        }
    }

    // NOVO: FUNÇÃO DE LOGIN
    suspend fun login(request: LoginRequest): CadastroResponse {
        val response = apiService.loginUsuario(request)

        if (response.isSuccessful) {
            // Retorna o corpo da resposta, que contém o ID do usuário e o Token
            return response.body() ?: throw IOException("Resposta de login vazia.")
        } else {
            // Trata erros (credenciais inválidas, etc.)
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            val errorMessage = "Falha no login. Erro HTTP ${response.code()}: $errorBody"
            throw IOException(errorMessage)
        }
    }
}