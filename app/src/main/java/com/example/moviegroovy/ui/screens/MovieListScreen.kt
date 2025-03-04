package com.example.moviegroovy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.moviegroovy.R
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.components.FullScreenLoader
import com.example.moviegroovy.ui.components.MovieCard
import com.example.moviegroovy.viewModel.movies.MovieListViewModel
import kotlinx.coroutines.launch

@Composable
fun MovieListScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieListViewModel = hiltViewModel(),
    onMovieClick: (Movie) -> Unit
) {
    val pagedMovies = viewModel.pagedMovies.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(colorResource(R.color.black_primary))
                .padding(8.dp),
            state = listState
        ) {
            when (pagedMovies.loadState.refresh) {
                is LoadState.Loading -> item { FullScreenLoader() }
                is LoadState.Error -> item { Text("Error loading movies", color = Color.Red, textAlign = TextAlign.Center) }
                else -> Unit
            }

            items(pagedMovies.itemCount) { index ->
                pagedMovies[index]?.let { movie ->
                    MovieCard(
                        movie = movie,
                        onClick = { onMovieClick(movie) }
                    )
                }
            }

            if (pagedMovies.loadState.append is LoadState.Loading) {
                item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
            }

            if (pagedMovies.loadState.append is LoadState.Error) {
                item { Text("Error Loading more movies", color = Color.Red) }
            }
        }

        FloatingActionButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(0)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = colorResource(R.color.purple_500)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_upward_24),
                contentDescription = "Scroll to top",
                tint = colorResource(R.color.white)
            )
        }
    }
}