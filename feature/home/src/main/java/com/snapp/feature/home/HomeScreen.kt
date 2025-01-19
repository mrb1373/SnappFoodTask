package com.snapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.snapp.designsystem.components.SnappProgressIndicator
import com.snapp.network.model.People
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onItemClick: (item: People) -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)) {

        SearchBar(onSearch = {
            viewModel.searchCharacter(it)
        })

        Spacer(modifier = Modifier.height(16.dp))

        SearchResult(modifier, uiState, onItemClick)
    }

}

private @Composable
fun SearchResult(modifier: Modifier = Modifier, uiState: HomeUiState, onItemClick: (item: People) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is HomeUiState.Initial -> {
                Text(text = "Search for a character")
            }
            is HomeUiState.Loading -> {
                SnappProgressIndicator(modifier)
            }

            is HomeUiState.Success -> {
                val data = uiState.data
                if (data.results.isEmpty()) {
                    Text("No results found")
                } else {
                    LazyColumn(Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(data.results , key = {it.name}) { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable{
                                        onItemClick(item)
                                    },
                                elevation = CardDefaults.cardElevation(4.dp),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = item.name,
                                    modifier = Modifier.padding(16.dp),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
            }

            is HomeUiState.Error -> {
                Text(text = uiState.message)
            }
        }

    }
}


@Composable
fun SearchBar(
    onSearch: (String) -> Unit,
    debounceTime: Long = 500L
) {
    var query by rememberSaveable { mutableStateOf("") }
    var isTyping by rememberSaveable { mutableStateOf(false) }

    // Trigger search after a debounce time
    LaunchedEffect(query) {
        isTyping = true
        delay(debounceTime)
        isTyping = false
        if (query.isNotEmpty())
            onSearch(query)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            colors = TextFieldDefaults.colors()
        )
        if (isTyping) {
            Text(
                text = "Typing...",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}