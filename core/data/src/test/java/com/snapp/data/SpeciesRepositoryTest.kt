package com.snapp.data

import android.util.Log
import com.snapp.data.species.SpeciesRepositoryImpl
import com.snapp.network.api.SpeciesService
import com.snapp.testing.peopleTestData
import com.snapp.testing.speciesTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class SpeciesRepositoryTest {
    private val speciesService = mockk<SpeciesService>( relaxed = true )
    private val speciesRepository = SpeciesRepositoryImpl(speciesService)

    @Test
    fun getCurrentSpecies_shouldReturnSuccess_whenNetworkCallSucceeds() = runTest {
        // Given
        val networkSpecies = speciesTestData

        val expectedResult = speciesTestData

        coEvery { speciesService.getSpecies(any()) } returns networkSpecies

        // When
        val result = speciesRepository.getSpecies("1")

        // Then
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrThrow() , expectedResult)
    }

    @Test
    fun getCurrentSpecies_shouldReturnFailure_whenNetworkCallFails() = runTest {
        mockkStatic(Log::class)
        val exception = Exception("Network error")
        coEvery { speciesService.getSpecies(any()) } throws exception
        every { Log.i(any(), any(), any()) } returns 0
        val result = speciesRepository.getSpecies("1")
        assertEquals(result.isFailure, true)
        assertEquals(result.exceptionOrNull(), exception)
    }
}