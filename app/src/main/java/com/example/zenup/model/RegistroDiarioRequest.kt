package org.example.model

data class RegistroDiarioRequest(
    val idUsuario: Long,
    val humor: Int,      // Ex: 1-5
    val energia: Int,    // Ex: 1-5
    val estresse: Int    // Ex: 1-5
)

