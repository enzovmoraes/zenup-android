package com.example.zenup.data.repository

import com.example.zenup.data.network.ApiClient
import com.example.zenup.data.api.ZenUpApi
import org.example.model.*
import java.io.IOException
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Repositório responsável pelos registros diários (check-in de humor, energia, estresse)
 */
class RegistroRepository(
    private val apiService: ZenUpApi = ApiClient.api,
    private val useMockData: Boolean = true // Toggle para usar dados mockados
) {
    /**
     * Envia o check-in diário (humor, energia, estresse) para a API.
     */
    suspend fun registrarCheckIn(request: RegistroDiarioRequest): RegistroDiarioResponse {
        if (useMockData) {
            // Simula delay de processamento da API
            delay(1500L)

            // Validação básica dos dados
            if (request.humor !in 1..6) {
                throw IOException("Valor de humor inválido. Deve estar entre 1 e 6.")
            }
            if (request.energia !in 1..5) {
                throw IOException("Valor de energia inválido. Deve estar entre 1 e 5.")
            }
            if (request.estresse !in 1..5) {
                throw IOException("Valor de estresse inválido. Deve estar entre 1 e 5.")
            }

            // Gera um ID mockado e data/hora atual
            val mockId = System.currentTimeMillis()
            val dataAtual = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))

            // Gera mensagem personalizada baseada nos valores
            val mensagem = gerarMensagemPersonalizada(request)

            return RegistroDiarioResponse(
                id = mockId,
                mensagem = mensagem,
                dataRegistro = dataAtual
            )
        }

        // Código original da API
        val response = apiService.registrarDiario(request)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de registro diário vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            val errorMessage = "Erro HTTP ${response.code()}: $errorBody"
            throw IOException(errorMessage)
        }
    }

    /**
     * Gera uma mensagem personalizada baseada nos valores registrados
     */
    private fun gerarMensagemPersonalizada(request: RegistroDiarioRequest): String {
        val humorTexto = when (request.humor) {
            1 -> "focado"
            2 -> "grato"
            3 -> "confiante"
            4 -> "inspirado"
            5 -> "frustrado"
            6 -> "desanimado"
            else -> "neutro"
        }

        val energiaTexto = when (request.energia) {
            1 -> "esgotado"
            2 -> "baixa"
            3 -> "moderada"
            4 -> "alta"
            5 -> "energizado"
            else -> "normal"
        }

        val estresseTexto = when (request.estresse) {
            1 -> "relaxado"
            2 -> "calmo"
            3 -> "moderado"
            4 -> "estressado"
            5 -> "muito estressado"
            else -> "normal"
        }

        return "Check-in registrado com sucesso!\n\n" +
                "Seu estado atual:\n" +
                "• Humor: $humorTexto\n" +
                "• Energia: $energiaTexto\n" +
                "• Estresse: $estresseTexto\n\n" +
                gerarDica(request)
    }

    /**
     * Gera uma dica baseada nos valores registrados
     */
    private fun gerarDica(request: RegistroDiarioRequest): String {
        return when {
            request.estresse >= 4 ->
                " Dica: Seus níveis de estresse estão altos. Que tal fazer uma pausa e praticar respiração profunda?"

            request.energia <= 2 ->
                " Dica: Sua energia está baixa. Considere fazer uma caminhada leve ou tomar um pouco de sol."

            request.humor >= 5 ->
                " Dica: Percebo que você pode estar passando por um momento difícil. Lembre-se: é okay não estar bem o tempo todo."

            request.humor <= 2 && request.energia >= 4 ->
                " Que ótimo ver você com boa energia e foco! Continue assim! "

            else ->
                " Continue acompanhando seu bem-estar diariamente. Cada registro é um passo importante!"
        }
    }
}