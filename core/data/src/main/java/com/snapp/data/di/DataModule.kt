package com.snapp.data.di

import com.snapp.data.detail.CharacterDetailRepository
import com.snapp.data.detail.CharacterDetailRepositoryImpl
import com.snapp.data.films.FilmRepository
import com.snapp.data.films.FilmRepositoryImpl
import com.snapp.data.people.PeopleRepository
import com.snapp.data.people.PeopleRepositoryImpl
import com.snapp.data.planet.PlanetRepository
import com.snapp.data.planet.PlanetRepositoryImpl
import com.snapp.data.search.SearchCharacterRepository
import com.snapp.data.search.SearchCharacterRepositoryImpl
import com.snapp.data.species.SpeciesRepository
import com.snapp.data.species.SpeciesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindSearchCharacterRepository(
        searchCharacterRepositoryImpl: SearchCharacterRepositoryImpl
    ): SearchCharacterRepository

    @Binds
    fun bindPeopleRepository(
        peopleRepositoryImpl: PeopleRepositoryImpl
    ): PeopleRepository

    @Binds
    fun bindSpeciesRepository(
        speciesRepositoryImpl: SpeciesRepositoryImpl
    ): SpeciesRepository

    @Binds
    fun bindFilmRepository(
        filmRepositoryImpl: FilmRepositoryImpl
    ): FilmRepository

    @Binds
    fun bindPlanetRepository(
        planetRepositoryImpl: PlanetRepositoryImpl
    ): PlanetRepository

    @Binds
    fun bindCharacterDetailRepository(
        characterDetailRepositoryImpl: CharacterDetailRepositoryImpl
    ): CharacterDetailRepository

}