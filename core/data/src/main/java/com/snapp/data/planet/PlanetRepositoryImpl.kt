package com.snapp.data.planet

import com.snapp.common.suspendRunCatching
import com.snapp.network.api.PlanetService
import com.snapp.network.model.Planet
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PlanetRepositoryImpl @Inject constructor(
    private val planetService: PlanetService
): PlanetRepository {
    override suspend fun getPlanet(id: String): Result<Planet> = coroutineScope {
        suspendRunCatching {
            planetService.getPlanet(id)
        }
    }
}