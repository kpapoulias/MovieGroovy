package com.example.moviegroovy.viewModel.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviegroovy.BuildConfig
import com.example.moviegroovy.data.repository.MovieRepositoryImpl
import com.example.moviegroovy.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {
    private val _movies = MutableStateFlow(MovieListState())
    val movies: StateFlow<MovieListState> = _movies

    init {
        fetchMovies()
        observeMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = _movies.value.copy(isLoading = true)
            try {
                repository.fetchMovies(BuildConfig.TMDB_API_KEY)
            } catch (e: Exception) {
                _movies.value = MovieListState(error = e.localizedMessage)
            }
        }
    }

    private fun observeMovies() {
        viewModelScope.launch {
            repository.movies.collectLatest { movieList ->
                _movies.value = _movies.value.copy(movies = movieList, isLoading = false)
            }
        }
    }
}

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)