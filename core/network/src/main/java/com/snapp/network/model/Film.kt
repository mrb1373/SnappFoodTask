package com.snapp.network.model

import com.google.gson.annotations.SerializedName

data class Film(
    val title: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String,
    val producer: String
)
