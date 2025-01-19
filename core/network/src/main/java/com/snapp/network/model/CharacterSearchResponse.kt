package com.snapp.network.model

data class CharacterSearchResponse(
    val count: Int,
    val results: List<People>
)
