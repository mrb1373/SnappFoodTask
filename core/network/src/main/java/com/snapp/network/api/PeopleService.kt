package com.snapp.network.api

import com.snapp.network.model.People
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleService {
    @GET("people/{id}")
    suspend fun getPeople(@Path("id") id: String): People
}