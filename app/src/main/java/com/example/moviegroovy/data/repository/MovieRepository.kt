package com.example.moviegroovy.data.repository

import com.example.moviegroovy.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    val movies: Flow<List<Movie>>
    suspend fun fetchMovies(apiKey: String)
}
