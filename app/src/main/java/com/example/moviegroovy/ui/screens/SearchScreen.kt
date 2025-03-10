package com.example.moviegroovy.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviegroovy.ui.components.SearchBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        SearchBar(
            searchQuery = "Search movies...",
            onSearchQueryChange = { },
            onClearClick = {}
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen()
}