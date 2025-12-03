package com.example.zenup.network

import com.example.zenup.data.api.ZenUpApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.zenup.network.provideOkHttpClient

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8000/"

    // variável mutável para armazenar token
    var authToken: String? = null

    private val client by lazy {
        provideOkHttpClient { authToken }
    }

    val api: ZenUpApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZenUpApi::class.java)
    }
}
