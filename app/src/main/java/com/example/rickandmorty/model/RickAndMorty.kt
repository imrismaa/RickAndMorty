package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class RickAndMorty(
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String
)
