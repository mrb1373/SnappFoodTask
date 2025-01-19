package com.snapp.network.api

import com.snapp.network.model.CharacterSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterSearchService {
    @GET("people/")
    suspend fun searchCharacter(@Query("search") query: String): CharacterSearchResponse
}