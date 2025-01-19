package com.snapp.network.api

import com.snapp.network.model.Planet
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetService {
    @GET("planets/{id}")
    suspend fun getPlanet(@Path("id") id: String): Planet
}