package com.snapp.data.search

import com.snapp.common.suspendRunCatching
import com.snapp.network.api.CharacterSearchService
import com.snapp.network.model.CharacterSearchResponse
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class SearchCharacterRepositoryImpl @Inject constructor(
    private val searchService: CharacterSearchService
): SearchCharacterRepository {
    override suspend fun searchCharacter(query: String): Result<CharacterSearchResponse> =
        coroutineScope {
            suspendRunCatching {
                searchService.searchCharacter(query)
            }
        }
}