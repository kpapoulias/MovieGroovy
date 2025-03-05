package com.example.moviegroovy

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.data.remote.MovieAPI
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val api: MovieAPI,
) : PagingSource<Int, Movie>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = api.getPopularMovies(nextPageNumber)
            val movies = response.results.map { it.toDomainMovie() }
            LoadResult.Page(
                data = movies,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (movies.isNotEmpty()) nextPageNumber + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}