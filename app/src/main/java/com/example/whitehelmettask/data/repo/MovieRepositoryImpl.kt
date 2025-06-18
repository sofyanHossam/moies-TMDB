package com.example.whitehelmettask.data.repo

import com.example.whitehelmettask.data.mapper.toDomain
import com.example.whitehelmettask.data.remote.TmdbApi
import com.example.whitehelmettask.domain.model.Movie
import com.example.whitehelmettask.domain.model.MovieDetails
import com.example.whitehelmettask.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class MovieRepositoryImpl(
    private val api: TmdbApi
) : MovieRepository {

    override fun getMovies(query: String?, page: Int): Flow<List<Movie>> = flow {
        val response = if (query.isNullOrBlank()) {
            api.getNowPlayingMovies(page = page)
        } else {
            api.searchMovies(query = query, page = page)
        }
        emit(response.results.map { it.toDomain() })
    }.catch { e ->
        throw Exception("Failed to fetch movies: ${e.message}")
    }

    override fun getSearchSuggestions(query: String): Flow<List<String>> = flow {
        if (query.isBlank()) emit(emptyList())
        else {
            val response = api.searchMovies(query = query, page = 1)
            val suggestions = response.results.mapNotNull { it.title }
            emit(suggestions.distinct().take(10))
        }
    }.catch { emit(emptyList()) }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return api.getMovieDetails(movieId).toDomain()
    }
}

