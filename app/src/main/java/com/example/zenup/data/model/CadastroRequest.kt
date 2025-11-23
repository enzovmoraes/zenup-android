// CadastroRequest.kt
package com.example.zenup.data.model

// Data class que define o formato de dados enviado no corpo da requisição POST de cadastro.
data class CadastroRequest(
    val nome: String,
    val email: String,
    val senha: String
)

