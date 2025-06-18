package com.example.whitehelmettask.domain.repo

import com.example.whitehelmettask.domain.model.Movie
import com.example.whitehelmettask.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(query: String?, page: Int): Flow<List<Movie>>
    fun getSearchSuggestions(query: String): Flow<List<String>>
    suspend fun getMovieDetails(movieId: Int): MovieDetails

}