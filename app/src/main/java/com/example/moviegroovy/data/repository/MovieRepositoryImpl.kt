package com.example.moviegroovy.data.repository

import com.example.moviegroovy.data.local.MovieDAO
import com.example.moviegroovy.data.remote.MovieAPI
import com.example.moviegroovy.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import com.example.moviegroovy.data.local.toDomainMovie

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI,
    private val dao: MovieDAO
) : MovieRepository {

    override val movies: Flow<List<Movie>> = dao.getMovies()
        .map { entities -> entities.map { it.toDomainMovie() } }

    override suspend fun fetchMovies(apiKey: String) {
        val response = api.getPopularMovies(apiKey)
        dao.insertMovies(response.results.map { it.toMovieEntity() })
    }
}