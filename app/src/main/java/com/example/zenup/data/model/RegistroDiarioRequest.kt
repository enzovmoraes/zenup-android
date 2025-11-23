// RegistroDiarioRequest.kt
package com.example.zenup.data.model

import java.time.LocalDateTime

data class RegistroDiarioRequest(
    val userId: Int,
    val humor: String,
    val energia: String,
    val estresse: String,
    val dataHoraRegistro: String = LocalDateTime.now().toString()
)


