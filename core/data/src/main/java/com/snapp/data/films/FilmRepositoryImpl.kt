package com.snapp.data.films

import com.snapp.common.suspendRunCatching
import com.snapp.network.api.FilmService
import com.snapp.network.model.Film
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val filmService: FilmService
): FilmRepository  {
    override suspend fun getFilm(id: String): Result<Film> = coroutineScope {
        suspendRunCatching {
            filmService.getFilm(id)
        }
    }
}