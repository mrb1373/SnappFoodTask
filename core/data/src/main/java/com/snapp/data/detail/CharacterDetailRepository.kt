package com.snapp.data.detail

interface CharacterDetailRepository {
    suspend fun getCharacterDetailRepository(id: String): Result<CharacterDetail>
}