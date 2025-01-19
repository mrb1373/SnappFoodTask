package com.snapp.feature.home

import com.snapp.network.model.CharacterSearchResponse

sealed class HomeUiState {
    data object Initial : HomeUiState()
    data object Loading : HomeUiState()
    data class Success(val data: CharacterSearchResponse) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}