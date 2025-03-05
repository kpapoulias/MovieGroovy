package com.example.moviegroovy.data.repository

import androidx.paging.PagingData
import com.example.moviegroovy.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    val movies: Flow<List<Movie>>
    suspend fun fetchMovies()
    fun getPagedMovies() : Flow<PagingData<Movie>>
    fun searchMovies(query: String): Flow<List<Movie>>
}
