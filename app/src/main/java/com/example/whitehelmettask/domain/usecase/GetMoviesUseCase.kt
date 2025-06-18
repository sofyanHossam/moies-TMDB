package com.example.whitehelmettask.domain.usecase


import com.example.whitehelmettask.domain.repo.MovieRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(query: String, page: Int) =
        repository.getMovies(query, page)
}


