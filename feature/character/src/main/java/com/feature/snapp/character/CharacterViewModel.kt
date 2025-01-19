package com.feature.snapp.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapp.data.detail.CharacterDetailRepository
import com.snapp.data.people.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val characterDetailRepository: CharacterDetailRepository
): ViewModel() {

    private val _characterDetailUiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val characterDetailUiState: StateFlow<CharacterDetailUiState> = _characterDetailUiState

    fun getCharacterDetail(id: String) {
        viewModelScope.launch {
            characterDetailRepository.getCharacterDetailRepository(id).apply {
                onSuccess {
                    _characterDetailUiState.emit(CharacterDetailUiState.Success(it))
                }

                onFailure {
                    _characterDetailUiState.emit(CharacterDetailUiState.Error)
                }
            }
        }
    }
}