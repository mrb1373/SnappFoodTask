package com.snapp.network.api

import com.snapp.network.model.Film
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmService {
    @GET("films/{id}")
    suspend fun getFilm(@Path("id") id: String): Film
}