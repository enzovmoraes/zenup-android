// LoginRequest.kt
package com.example.zenup.data.model

// Data class que define o formato de dados enviado no corpo da requisição POST de login.
data class LoginRequest(
    val email: String,
    val senha: String
)