package com.example.whitehelmettask.presentation.home.state

import com.example.whitehelmettask.domain.model.Movie

sealed class MovieUiState {
    object Loading : MovieUiState()
    data class Success(val movies: List<Movie>) : MovieUiState()
    data class Error(val message: String) : MovieUiState()
}