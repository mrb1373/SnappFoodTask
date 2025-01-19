package com.feature.snapp.character

import com.snapp.data.detail.CharacterDetail

sealed class CharacterDetailUiState {
    data object Loading: CharacterDetailUiState()
    data object Error: CharacterDetailUiState()
    data class Success(val characterDetail: CharacterDetail): CharacterDetailUiState()
}