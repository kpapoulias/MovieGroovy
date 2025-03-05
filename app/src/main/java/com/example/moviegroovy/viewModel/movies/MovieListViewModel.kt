package com.example.moviegroovy.viewModel.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow(MovieListState())
    val searchResults: StateFlow<MovieListState> = _searchResults.asStateFlow()

    val pagedMovies: Flow<PagingData<Movie>> = repository.getPagedMovies()
        .cachedIn(viewModelScope)

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.isNotEmpty()) {
            searchMovies(query)
        } else {
            _searchResults.value = MovieListState()
        }
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            repository.searchMovies(query)
                .onStart { _searchResults.value = MovieListState(isLoading = true) }
                .catch { e -> _searchResults.value = MovieListState(error = e.message) }
                .collect { movies -> _searchResults.value = MovieListState(movies = movies) }
        }
    }
}

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)