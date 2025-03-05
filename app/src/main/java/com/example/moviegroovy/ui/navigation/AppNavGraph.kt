package com.example.moviegroovy.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.components.TopAppBarWithSearch
import com.example.moviegroovy.ui.screens.MainScreen
import com.example.moviegroovy.ui.screens.MovieDetailScreen
import com.example.moviegroovy.ui.screens.Screen
import com.example.moviegroovy.viewModel.MainViewModel
import com.google.gson.Gson
import java.net.URLDecoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(navController: NavHostController) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var showSearchBar by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBarWithSearch(
                scrollBehavior = scrollBehavior,
                showSearchBar = showSearchBar,
                searchQuery = searchQuery,
                onSearchClick = { showSearchBar = !showSearchBar },
                onSearchQueryChange = { searchQuery = it },
                onClearClick = { searchQuery = "" }
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