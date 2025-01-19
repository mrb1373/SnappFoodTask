package com.snapp.network.api

import com.snapp.network.model.Species
import retrofit2.http.GET
import retrofit2.http.Path

interface SpeciesService {
    @GET("species/{id}")
    suspend fun getSpecies(@Path("id") id: String): Species
}