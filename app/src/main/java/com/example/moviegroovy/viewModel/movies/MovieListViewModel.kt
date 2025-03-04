package com.example.moviegroovy.viewModel.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviegroovy.data.model.Movie
import com.example.moviegroovy.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl
) : ViewModel() {
    val pagedMovies: Flow<PagingData<Movie>> = repository.getPagedMovies()
        .cachedIn(viewModelScope) // Ensures data does not reload when the device rotates or UI recomposes.
}

data class MovieListState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)