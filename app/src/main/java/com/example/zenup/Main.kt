package com.example.zenup

import kotlinx.coroutines.runBlocking
import org.example.model.ChatRequest
import org.example.model.LoginRequest
import com.example.zenup.data.repository.AuthRepository

fun main() = runBlocking {

    val authRepository = AuthRepository()

    // 1. Login usando o repositório
    try {
        val loginResponse = authRepository.login(
            LoginRequest(
                email = "usuario@exemplo.com",
                senha = "senha123"
            )
        )
        println("Token recebido: ${loginResponse.token}")

        // 2. Chat usando o repositório
        val chatResponse = authRepository.chat(
            ChatRequest(id = 123, texto = "estou me sentindo muito triste")
        )
        println("Resposta do chat: ${chatResponse.mensagem}")

        // 3. Resumo usando o repositório
        val resumoResponse = authRepository.resumo(idUsuario = 123L)
        println("Resumo: ${resumoResponse.resumo}")

    } catch (e: Exception) {
        println("Erro: ${e.message}")
    }
}