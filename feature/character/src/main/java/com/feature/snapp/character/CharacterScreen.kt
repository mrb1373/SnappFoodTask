package com.feature.snapp.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapp.common.cmToFeet
import com.snapp.common.cmToInches
import com.snapp.common.retrieveId
import com.snapp.data.detail.CharacterDetail
import com.snapp.data.detail.FilmDetail
import com.snapp.designsystem.components.BodyText
import com.snapp.designsystem.components.CaptionText
import com.snapp.designsystem.components.HeaderText
import com.snapp.network.model.People

@Composable
fun CharacterScreen(modifier: Modifier = Modifier, character: String) {
    val viewModel: CharacterViewModel = hiltViewModel()
    viewModel.getCharacterDetail(character.retrieveId())
    val uiState by viewModel.characterDetailUiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center) {
        PeopleDetail(modifier = modifier, uiState = uiState)
    }
}

@Composable
fun PeopleDetail(modifier: Modifier = Modifier, uiState: CharacterDetailUiState) {
    when(uiState) {
        is CharacterDetailUiState.Error -> {
            Text(text = "Error")
        }
        is CharacterDetailUiState.Loading -> {
            CircularProgressIndicator()
        }
        is CharacterDetailUiState.Success -> {
            DetailSection(modifier = modifier.fillMaxSize(), characterDetail = uiState.characterDetail)
        }
    }
}


@Composable
fun DetailSection(modifier: Modifier = Modifier, characterDetail: CharacterDetail) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderText(text = characterDetail.name)
        BodyText(text = "Birth Year: ${characterDetail.birthYear}")
        BodyText(text = getHeightText(characterDetail.height))
        BodyText(text = "Species: ${characterDetail.speciesName}")
        BodyText(text = "Home World: ${characterDetail.homeWorld}")
        BodyText(text = "Population: ${characterDetail.population}")
        Spacer(modifier = Modifier.height(16.dp))
        FilmDetailSection(modifier = modifier, films = characterDetail.films)
    }
}

@Composable
fun FilmDetailSection(modifier: Modifier = Modifier, films: List<FilmDetail>) {
    Column(modifier.fillMaxWidth()) {
        HeaderText(text = "Films:")
        films.forEach {
            BodyText(text = "title : ${it.title}")
            CaptionText(text = "opening crawl : ${it.openingCrawl}", modifier)
        }
    }
}
private fun getHeightText(height: String): String {
    val heightInCm = height.toIntOrNull() ?: 0
    val heightInInches = heightInCm.cmToInches()
    val heightInFeet = heightInCm.cmToFeet()

    return "Height: $heightInCm cm - $heightInInches inches - $heightInFeet feet"
}