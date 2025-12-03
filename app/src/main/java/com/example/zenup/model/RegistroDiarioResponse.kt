package org.example.model

data class RegistroDiarioResponse(
    val id: Long,
    val mensagem: String,
    val dataRegistro: String? = null
)