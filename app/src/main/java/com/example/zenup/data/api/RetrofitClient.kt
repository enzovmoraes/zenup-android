// RetrofitClient.kt
package com.example.zenup.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // ATENÇÃO: Verifique o BASE_URL. Para emuladores Android é 10.0.2.2
    private const val BASE_URL = "http://10.0.2.2:8080/api/v1/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            // Define o conversor JSON
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Instância que será usada pelos Repositórios (AuthApiService)
    val authApiService: AuthApiService by lazy {
        retrofit.create(AuthApiService::class.java)
    }
}