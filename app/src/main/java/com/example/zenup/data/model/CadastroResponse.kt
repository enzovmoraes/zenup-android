package com.example.zenup.data.model

class CadastroResponse {

    // Data class que define o formato da resposta esperada do servidor após o cadastro.
    data class CadastroResponse(
        val userId: Int,
        val token: String,
        val message: String? = null
    )
}