package com.example.moviegroovy.ui.screens

import androidx.compose.runtime.Composable
import com.example.moviegroovy.data.model.Movie

@Composable
fun MainScreen(
    onMovieClick: (Movie) -> Unit
) {
    MovieListScreen(
        onMovieClick = onMovieClick
    )
}
