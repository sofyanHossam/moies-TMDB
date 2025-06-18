package com.example.whitehelmettask.data.remote


import com.example.whitehelmettask.BuildConfig
import com.example.whitehelmettask.data.dto.MovieDetailsDto
import com.example.whitehelmettask.data.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
 FIXME: FOR WHITHELMET TEAAM:
   here is just make hard coded api for u to use but i usually use
   const val API_KEY: String = BuildConfig.TMDB_API_KEY
   for secure api key
 */
//const val API_KEY: String = BuildConfig.TMDB_API_KEY
const val API_KEY: String = "8cb3dd51149857e590d0542b897ccf4b"
interface TmdbApi {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse


    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailsDto
}
