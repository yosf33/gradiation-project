package com.example.gradiationproject.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/") // Replace with your actual base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: PdfApiService by lazy {
        retrofit.create(PdfApiService::class.java)
    }
}
