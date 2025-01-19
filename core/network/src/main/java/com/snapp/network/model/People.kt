package com.snapp.network.model

import com.google.gson.annotations.SerializedName

data class People(
    val name: String,
    val height: String,
    val mass: String,
    @SerializedName("birth_year")
    val birthYear: String,
    val gender: String,
    @SerializedName("homeworld")
    val homeWorld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val url: String
)