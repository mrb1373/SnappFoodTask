package com.snapp.data

import android.util.Log
import com.snapp.common.retrieveId
import com.snapp.data.people.PeopleRepositoryImpl
import com.snapp.network.api.PeopleService
import com.snapp.testing.peopleTestData
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class PeopleRepositoryTest {
    private val peopleService = mockk<PeopleService>( relaxed = true )
    private val peopleRepository = PeopleRepositoryImpl(peopleService)

    @Test
    fun getCurrentCharacter_shouldReturnSuccess_whenNetworkCallSucceeds() = runTest {
        // Given
        val networkCharacter = peopleTestData

        val expectedResult = peopleTestData

        coEvery { peopleService.getPeople(any()) } returns networkCharacter

        // When
        val result = peopleRepository.getPeople("1")

        // Then
        assertEquals(result.isSuccess, true)
        assertEquals(result.getOrThrow() , expectedResult)
    }

    @Test
    fun getCurrentCharacter_shouldReturnFailure_whenNetworkCallFails() = runTest {
        mockkStatic(Log::class)
        val exception = Exception("Network error")
        coEvery { peopleService.getPeople(any()) } throws exception
        every { Log.i(any(), any(), any()) } returns 0
        val result = peopleRepository.getPeople("1")
        assertEquals(result.isFailure, true)
        assertEquals(result.exceptionOrNull(), exception)
    }

    @Test
    fun test_retrieveId_from_url() {
        val url = peopleTestData.url

        assertEquals(url.retrieveId(), "1")
    }

}