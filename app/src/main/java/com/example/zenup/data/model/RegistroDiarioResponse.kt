package com.example.zenup.data.model

import java.time.LocalDateTime

data class RegistroDiarioResponse(
    val idRegistro: Int? = null,
    val status: String, // Ex: "Sucesso"
    val mensagem: String
)