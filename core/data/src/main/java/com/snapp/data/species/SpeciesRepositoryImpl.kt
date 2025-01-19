package com.snapp.data.species

import com.snapp.common.suspendRunCatching
import com.snapp.network.api.SpeciesService
import com.snapp.network.model.Species
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SpeciesRepositoryImpl @Inject constructor(
    private val speciesService: SpeciesService
): SpeciesRepository {
    override suspend fun getSpecies(id: String): Result<Species> = coroutineScope {
        suspendRunCatching {
            speciesService.getSpecies(id)
        }
    }
}