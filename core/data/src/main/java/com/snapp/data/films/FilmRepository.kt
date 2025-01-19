package com.snapp.data.films

import com.snapp.network.model.Film

interface FilmRepository {
    suspend fun getFilm(id: String): Result<Film>
}