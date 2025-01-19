package com.snapp.data.species

import com.snapp.network.model.Species

interface SpeciesRepository {
    suspend fun getSpecies(id: String): Result<Species>
}