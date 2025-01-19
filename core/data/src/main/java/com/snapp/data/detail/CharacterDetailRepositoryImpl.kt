package com.snapp.data.detail

import com.snapp.common.retrieveId
import com.snapp.common.suspendRunCatching
import com.snapp.network.api.FilmService
import com.snapp.network.api.PeopleService
import com.snapp.network.api.PlanetService
import com.snapp.network.api.SpeciesService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val peopleService: PeopleService,
    private val speciesService: SpeciesService,
    private val planetService: PlanetService,
    private val filmService: FilmService
): CharacterDetailRepository {
    override suspend fun getCharacterDetailRepository(id: String): Result<CharacterDetail> = coroutineScope {
        suspendRunCatching {
            val people = peopleService.getPeople(id)
            val speciesUrl = people.species.firstOrNull()
            val filmsUrlIds = people.films.map { it.retrieveId() }
            val homeWorldId = people.homeWorld.retrieveId()

            val speciesDeferred = if (speciesUrl != null) {
                val speciesId = speciesUrl.retrieveId()
                async { speciesService.getSpecies(speciesId) }
            } else null

            val homeWorldsDeferred = async { planetService.getPlanet(homeWorldId) }

            val filmsDeferred = filmsUrlIds.map {
                async { filmService.getFilm(it) }
            }

            val films = filmsDeferred.awaitAll()
            val species = speciesDeferred?.await()
            val homeWorld = homeWorldsDeferred.await()

            CharacterDetail(
                name = people.name,
                birthYear = people.birthYear,
                height = people.height,
                speciesName = species?.name ?: "no species found",
                homeWorld = homeWorld.name,
                population = homeWorld.population,
                films = films.map { FilmDetail(it.title, it.openingCrawl) }
            )
        }
    }
}