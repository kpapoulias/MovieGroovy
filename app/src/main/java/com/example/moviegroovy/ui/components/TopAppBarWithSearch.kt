package com.example.moviegroovy.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithSearch(
    scrollBehavior: TopAppBarScrollBehavior,
    showSearchBar: Boolean,
    searchQuery: String,
    onSearchClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onClearClick: () -> Unit
) {
    Column {
        TopNavBar(
            showSearchBar = showSearchBar,
            onMenuClick = { /* Handle menu click */ },
            onSearchClick = onSearchClick,
            scrollBehavior = scrollBehavior
        )
        if (showSearchBar) {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = onSearchQueryChange,
                onClearClick = onClearClick
            )
        }
    }
}