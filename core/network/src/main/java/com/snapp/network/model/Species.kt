package com.snapp.network.model

import com.google.gson.annotations.SerializedName

data class Species(
    val name: String,
    val classification: String,
    val designation: String,
    @SerializedName("average_height")
    val averageHeight: String,
    val language: String
)
