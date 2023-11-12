package com.example.rickandmorty.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {
    fun getInstance(): APIService {
        val mOkHttpInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(mOkHttpInterceptor).build()
        val builder = Retrofit.Builder().baseUrl("https://rickandmortyapi.com/")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(APIService::class.java)
    }
}