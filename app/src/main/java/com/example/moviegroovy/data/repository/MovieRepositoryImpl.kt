package com.example.moviegroovy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviegroovy.MoviePagingSource
import com.example.moviegroovy.data.local.MovieDAO
import com.example.moviegroovy.data.remote.MovieAPI
import com.example.moviegroovy.data.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import com.example.moviegroovy.data.local.toDomainMovie
import javax.inject.Named

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val api: MovieAPI,
    private val dao: MovieDAO,
    @Named("tmdb_api_key") private val apiKey: String
) : MovieRepository {

    override val movies: Flow<List<Movie>> = dao.getMovies()
        .map { entities -> entities.map { it.toDomainMovie() } }

    override suspend fun fetchMovies() {
        val response = api.getPopularMovies(apiKey, page = 1)
        dao.insertMovies(response.results.map { it.toMovieEntity() })
    }

    override fun getPagedMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(api, apiKey) }
        ).flow
    }
}