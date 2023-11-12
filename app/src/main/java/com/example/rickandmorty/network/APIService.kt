package com.example.rickandmorty.network

import com.example.rickandmorty.model.RickAndMortyData
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("api/character")
    fun getData(): Call<RickAndMortyData>
}