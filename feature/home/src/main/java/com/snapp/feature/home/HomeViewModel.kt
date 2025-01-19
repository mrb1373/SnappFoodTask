package com.snapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapp.data.search.SearchCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchCharacterRepository: SearchCharacterRepository
): ViewModel() {
    private val _uiStateFlow = MutableStateFlow<HomeUiState>(HomeUiState.Initial)
    val uiStateFlow: StateFlow<HomeUiState> = _uiStateFlow

    fun searchCharacter(query: String) {
        _uiStateFlow.value = HomeUiState.Loading
        viewModelScope.launch {
            searchCharacterRepository.searchCharacter(query).apply {
                onSuccess {
                    _uiStateFlow.emit(HomeUiState.Success(it))
                }
                onFailure {
                    _uiStateFlow.emit(HomeUiState.Error(it.message ?: "Unknown error"))
                }
            }
        }
    }
}