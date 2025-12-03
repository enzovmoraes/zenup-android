package com.example.zenup.data.repository

import com.example.zenup.data.network.ApiClient
import com.example.zenup.data.api.ZenUpApi
import org.example.model.*
import java.io.IOException

/**
 * Repositório responsável pelos registros diários (check-in de humor, energia, estresse)
 */
class RegistroRepository(
    private val apiService: ZenUpApi = ApiClient.api
) {
    /**
     * Envia o check-in diário (humor, energia, estresse) para a API.
     */
    suspend fun registrarCheckIn(request: RegistroDiarioRequest): RegistroDiarioResponse {
        val response = apiService.registrarDiario(request)

        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de registro diário vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            val errorMessage = "Erro HTTP ${response.code()}: $errorBody"
            throw IOException(errorMessage)
        }
    }
}