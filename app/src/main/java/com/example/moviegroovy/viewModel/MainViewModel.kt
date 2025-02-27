package com.example.moviegroovy.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.moviegroovy.data.model.Movie

class MainViewModel : ViewModel() {
    private val _navigateToMovieDetail = MutableStateFlow<Movie?>(null)
    val navigateToMovieDetail: StateFlow<Movie?> = _navigateToMovieDetail

    fun onMovieClicked(movie: Movie) {
        viewModelScope.launch {
            _navigateToMovieDetail.emit(movie)
        }
    }

    fun onNavigationHandled() {
        viewModelScope.launch {
            _navigateToMovieDetail.emit(null)
        }
    }
}
