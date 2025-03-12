package com.example.moviegroovy.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor() : ViewModel() {
    private val _isDrawerOpen = mutableStateOf(false)
    val isDrawerOpen: State<Boolean> = _isDrawerOpen

    fun openDrawer() {
        _isDrawerOpen.value = true
    }

    fun closeDrawer() {
        _isDrawerOpen.value = false
    }

    fun toggleDrawer() {
        _isDrawerOpen.value = !_isDrawerOpen.value
    }
}