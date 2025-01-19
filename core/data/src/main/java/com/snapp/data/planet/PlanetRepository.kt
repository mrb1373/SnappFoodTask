package com.snapp.data.planet

import com.snapp.network.model.Planet

interface PlanetRepository {
    suspend fun getPlanet(id: String): Result<Planet>
}