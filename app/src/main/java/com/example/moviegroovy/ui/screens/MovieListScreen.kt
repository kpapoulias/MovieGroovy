package com.example.moviegroovy.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.moviegroovy.R
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.components.MovieCard
import com.example.moviegroovy.viewModel.movies.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val movieState by viewModel.movies.collectAsStateWithLifecycle()
    Log.d("MovieListScreen", "[DEBUG] movieState.movies size: ${movieState.movies.size}")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.gray))
            .padding(8.dp)
    ) {
        items(movieState.movies) { movie ->
            MovieCard(
                movie = movie,
                onClick = { onMovieClick(movie) }
            )
        }
    }
}