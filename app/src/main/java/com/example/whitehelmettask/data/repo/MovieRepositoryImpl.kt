package com.example.whitehelmettask.data.repo

import com.example.whitehelmettask.data.local.dao.MovieDao
import com.example.whitehelmettask.data.mapper.toDomain
import com.example.whitehelmettask.data.mapper.toEntity
import com.example.whitehelmettask.data.remote.TmdbApi
import com.example.whitehelmettask.domain.model.Movie
import com.example.whitehelmettask.domain.model.MovieDetails
import com.example.whitehelmettask.domain.repo.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class MovieRepositoryImpl(
    private val api: TmdbApi,
    private val movieDao: MovieDao
) : MovieRepository {

    override fun getMovies(query: String?, page: Int): Flow<List<Movie>> {
        return flow {
            val searchQuery = query?.takeIf { it.isNotBlank() } ?: "now_playing"
            val offset = (page - 1) * 20
            val limit = 20

            val movies = try {
                val response = if (query.isNullOrBlank()) {
                    api.getNowPlayingMovies(page = page)
                } else {
                    api.searchMovies(query = query, page = page)
                }

                val domain = response.results.map { it.toDomain() }
                if (page == 1) movieDao.clearMoviesByQuery(searchQuery)
                movieDao.insertMovies(domain.map { it.toEntity(searchQuery) })
                domain
            } catch (e: Exception) {
                movieDao.getMoviesByQuery(searchQuery, limit, offset)
                    .map { it.toDomain() }
            }

            emit(movies)
        }
    }

    override fun getSearchSuggestions(query: String): Flow<List<String>> = flow {
        try {
            val response = api.searchMovies(query = query, page = 1)
            val suggestions = response.results.mapNotNull { it.title }
            emit(suggestions.distinct().take(10))
        } catch (e: Exception) {
            val cached = movieDao.getSuggestions(query)
            emit(cached)
        }
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return api.getMovieDetails(movieId).toDomain()
    }
}
