package com.example.whitehelmettask.presentation.details.state

import com.example.whitehelmettask.domain.model.MovieDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val movie: MovieDetails? = null,
    val error: String? = null
)
