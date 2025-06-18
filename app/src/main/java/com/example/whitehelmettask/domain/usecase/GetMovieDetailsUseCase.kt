package com.example.whitehelmettask.domain.usecase

import com.example.whitehelmettask.domain.model.MovieDetails
import javax.inject.Inject
import com.example.whitehelmettask.domain.repo.MovieRepository

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int): MovieDetails {
        return repository.getMovieDetails(id)
    }
}
