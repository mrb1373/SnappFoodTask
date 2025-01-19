package com.feature.snapp.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapp.common.cmToFeet
import com.snapp.common.cmToInches
import com.snapp.common.retrieveId
import com.snapp.data.detail.CharacterDetail
import com.snapp.data.detail.FilmDetail
import com.snapp.designsystem.components.CharacterDetailItem
import com.snapp.designsystem.components.HeaderText

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
            Text(text = stringResource(R.string.error))
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
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                with(characterDetail) {
                    CharacterDetailItem(stringResource(R.string.birth_year), birthYear)
                    CharacterDetailItem(stringResource(R.string.height), getHeightText(height))
                    CharacterDetailItem(stringResource(R.string.species), speciesName)
                    CharacterDetailItem(stringResource(R.string.home_world), homeWorld)
                    CharacterDetailItem(stringResource(R.string.population), population)
                }
            }
        }
        // Films Section
        Text(
            text = stringResource(R.string.films),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp, bottom = 4.dp)
        )
        FilmDetailSection(modifier = modifier, films = characterDetail.films)
    }
}

@Composable
fun FilmDetailSection(modifier: Modifier = Modifier, films: List<FilmDetail>) {
    films.forEach { (title, openingCrawl) ->
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(modifier = modifier.padding(16.dp)) {
                Text(
                    text = stringResource(R.string.title, title),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.opening_crawl, openingCrawl),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
private fun getHeightText(height: String): String {
    val heightInCm = height.toIntOrNull() ?: 0
    val heightInInches = heightInCm.cmToInches()
    val heightInFeet = heightInCm.cmToFeet()

    return "Height: $heightInCm cm - $heightInInches inches - $heightInFeet feet"
}