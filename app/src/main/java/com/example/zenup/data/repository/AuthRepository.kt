package com.example.zenup.data.repository

import com.example.zenup.data.network.ApiClient
import com.example.zenup.data.api.ZenUpApi
import org.example.model.*
import java.io.IOException
import kotlinx.coroutines.delay

class AuthRepository(
    private val apiService: ZenUpApi = ApiClient.api,
    private val useMockData: Boolean = true // Toggle para usar dados mockados
) {

    // FUNÇÃO DE LOGIN (MOCKADA)
    suspend fun login(request: LoginRequest): LoginResponse {
        if (useMockData) {
            // Simula delay de rede
            delay(1000L)

            // Simula validação de credenciais
            if (request.email.isNotBlank() && request.senha.isNotBlank()) {
                val mockToken = "mock_token_${System.currentTimeMillis()}"
                ApiClient.authToken = mockToken

                return LoginResponse(token = mockToken)
            } else {
                throw IOException("Email ou senha inválidos")
            }
        }

        // Código original da API (caso useMockData seja false)
        val response = apiService.login(request)
        if (response.isSuccessful) {
            val loginResponse = response.body() ?: throw IOException("Resposta de login vazia.")
            ApiClient.authToken = loginResponse.token
            return loginResponse
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            val errorMessage = "Falha no login. Erro HTTP ${response.code()}: $errorBody"
            throw IOException(errorMessage)
        }
    }

    // FUNÇÃO DE CHAT (MOCKADA)
    suspend fun chat(request: ChatRequest): ChatResponse {
        if (useMockData) {
            // Simula delay de processamento
            delay(1500L)

            // Respostas mockadas baseadas no texto da mensagem
            val resposta = when {
                request.texto.contains("triste", ignoreCase = true) ->
                    "Entendo que você está se sentindo triste. É importante validar seus sentimentos. Que tal experimentar algumas técnicas de respiração ou uma caminhada ao ar livre?"

                request.texto.contains("ansioso", ignoreCase = true) ||
                        request.texto.contains("ansiedade", ignoreCase = true) ->
                    "A ansiedade pode ser desafiadora. Vamos tentar um exercício de respiração: inspire profundamente por 4 segundos, segure por 4, e expire por 4. Repita algumas vezes."

                request.texto.contains("estressado", ignoreCase = true) ||
                        request.texto.contains("estresse", ignoreCase = true) ->
                    "O estresse faz parte da vida, mas podemos gerenciá-lo. Você já tentou meditação guiada ou pausas regulares durante o dia?"

                request.texto.contains("olá", ignoreCase = true) ||
                        request.texto.contains("oi", ignoreCase = true) ->
                    "Olá! Como posso te ajudar hoje? Estou aqui para ouvir e apoiar você em sua jornada de bem-estar."

                request.texto.contains("obrigado", ignoreCase = true) ||
                        request.texto.contains("obrigada", ignoreCase = true) ->
                    "Por nada! Fico feliz em poder ajudar. Estou sempre aqui quando precisar conversar."

                else ->
                    "Entendo. Conte-me mais sobre como você está se sentindo. Estou aqui para te ouvir e oferecer suporte."
            }

            return ChatResponse(mensagem = resposta)
        }

        // Código original da API
        val response = apiService.chat(request)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de chat vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            throw IOException("Erro no chat. HTTP ${response.code()}: $errorBody")
        }
    }

    // FUNÇÃO DE RESUMO (MOCKADA)
    suspend fun resumo(idUsuario: Long): ResumoResponse {
        if (useMockData) {
            delay(800L)

            val mockResumo = """
                📊 Resumo da Semana
                
                Humor: Você tem alternado entre sentimentos de confiança e momentos de frustração. 
                
                Energia: Seus níveis de energia estão moderados, com picos de energia após exercícios.
                
                Estresse: Você tem experimentado níveis moderados de estresse, especialmente no meio da semana.
                
                💡 Recomendação: Continue mantendo uma rotina de autocuidado e considere incluir mais momentos de pausa durante o dia.
            """.trimIndent()

            return ResumoResponse(resumo = mockResumo)
        }

        // Código original da API
        val response = apiService.resumo(idUsuario)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Resposta de resumo vazia.")
        } else {
            val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
            throw IOException("Erro ao buscar resumo. HTTP ${response.code()}: $errorBody")
        }
    }
}