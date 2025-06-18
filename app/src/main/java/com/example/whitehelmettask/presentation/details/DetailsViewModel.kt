package com.example.whitehelmettask.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitehelmettask.domain.repo.MovieRepository
import com.example.whitehelmettask.domain.usecase.GetMovieDetailsUseCase
import com.example.whitehelmettask.presentation.details.state.DetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailsUiState())
        private set

    fun loadMovieDetails(id: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val movie = getMovieDetailsUseCase(id)
                state = state.copy(movie = movie, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message, isLoading = false)
            }
        }
    }
}



