package com.example.moviegroovy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviegroovy.MoviePagingSource
import com.example.moviegroovy.data.local.MovieDAO
import com.example.moviegroovy.data.local.toDomainMovie
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.data.remote.MovieAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI,
    private val dao: MovieDAO
) : MovieRepository {

    override val movies: Flow<List<Movie>> = dao.getMovies()
        .map { entities -> entities.map { it.toDomainMovie() } }

    override suspend fun fetchMovies() {
        val response = api.getPopularMovies(page = 1)
        dao.insertMovies(response.results.map { it.toMovieEntity() })
    }

    override fun getPagedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(api) }
        ).flow
    }

    override fun searchMovies(query: String): Flow<List<Movie>> = flow {
        try {
            val searchResponse = api.searchMovie(query)
            val movies = searchResponse.results.map { it.toDomainMovie() }
            emit(movies)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }.flowOn(Dispatchers.IO)
}