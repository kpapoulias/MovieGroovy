package com.example.moviegroovy.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.components.TopNavBar
import com.example.moviegroovy.ui.screens.MainScreen
import com.example.moviegroovy.ui.screens.MovieDetailScreen
import com.example.moviegroovy.viewModel.MainViewModel
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

sealed class Screen(val route: String) {
    data object Main : Screen("main")
    data object MovieList : Screen("movie_list")
    data object MovieDetail : Screen("movie_detail/{movieJson}") {
        fun createRoute(movie: Movie): String {
            val movieJson = Gson().toJson(movie)
            return "movie_detail/${URLEncoder.encode(movieJson, "UTF-8")}"
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        topBar = {
            TopNavBar(
                onMenuClick = { /* Handle menu click */ },
                onSearchClick = { /* Handle search click */ },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Main.route) {
                val viewModel: MainViewModel = hiltViewModel()
                MainScreen(navController, viewModel, scrollBehavior)
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
}