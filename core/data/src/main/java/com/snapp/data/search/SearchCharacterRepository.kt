package com.snapp.data.search

import com.snapp.network.model.CharacterSearchResponse

interface SearchCharacterRepository {
    suspend fun searchCharacter(query: String): Result<CharacterSearchResponse>
}