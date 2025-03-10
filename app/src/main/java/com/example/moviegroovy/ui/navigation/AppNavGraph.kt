package com.example.moviegroovy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.screens.MainScreen
import com.example.moviegroovy.ui.screens.MovieDetailScreen
import com.example.moviegroovy.ui.screens.Screen
import com.example.moviegroovy.viewModel.MainViewModel
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val viewModel: MainViewModel = hiltViewModel()
    val navigateToMovieDetail by viewModel.navigateToMovieDetail.collectAsState()

    LaunchedEffect(navigateToMovieDetail) {
        navigateToMovieDetail?.let { movie ->
            val movieJson = Gson().toJson(movie)
            val encodedMovieJson = URLEncoder.encode(movieJson, "UTF-8")
            navController.navigate("movie_detail/$encodedMovieJson")
            viewModel.onNavigationHandled() // Reset state after navigation
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main.route,
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                onMovieClick = { movie -> viewModel.onMovieClicked(movie) }
            )
        }

        composable(
            route = Screen.MovieDetail.route,
            arguments = listOf(navArgument("movieJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val movieJson = backStackEntry.arguments?.getString("movieJson")
            val decodedMovieJson = movieJson?.let { URLDecoder.decode(it, "UTF-8") }
            val movie = Gson().fromJson(decodedMovieJson, Movie::class.java)

            MovieDetailScreen(
                movie = movie,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}