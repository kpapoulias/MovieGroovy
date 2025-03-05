package com.example.moviegroovy.ui.screens

import com.example.moviegroovy.data.model.Movie
import com.google.gson.Gson
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