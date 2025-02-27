package com.example.moviegroovy.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviegroovy.viewModel.MainViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
    scrollBehavior: TopAppBarScrollBehavior
) {
    val navigateToMovieDetail by viewModel.navigateToMovieDetail.collectAsState()

    LaunchedEffect(navigateToMovieDetail) {
        navigateToMovieDetail?.let { movie ->
            val movieJson = Gson().toJson(movie)
            val encodedMovieJson = URLEncoder.encode(movieJson, "UTF-8")
            navController.navigate("movie_detail/$encodedMovieJson")
            viewModel.onNavigationHandled()  // Reset state after navigation
        }
    }

    MovieListScreen(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        onMovieClick = { movie -> viewModel.onMovieClicked(movie) }
    )
}
