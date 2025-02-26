package com.example.moviegroovy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.ui.screens.MovieDetailScreen
import com.example.moviegroovy.ui.screens.MovieListScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

sealed class Screen(val route: String) {
    data object MovieList : Screen("movie_list")
    data object MovieDetail : Screen("movie_detail/{movieJson}") {
        fun createRoute(movie: Movie): String {
            val movieJson = Gson().toJson(movie)
            return "movie_detail/${URLEncoder.encode(movieJson, "UTF-8")}"
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.MovieList.route) {
        composable(Screen.MovieList.route) {
            MovieListScreen(
                onMovieClick = { movie ->
                    navController.navigate(Screen.MovieDetail.createRoute(movie))
                }
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