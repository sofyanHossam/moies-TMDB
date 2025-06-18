package com.example.whitehelmettask.di

import com.example.whitehelmettask.data.remote.TmdbApi
import com.example.whitehelmettask.data.repo.MovieRepositoryImpl
import com.example.whitehelmettask.domain.repo.MovieRepository
import com.example.whitehelmettask.domain.usecase.GetMovieDetailsUseCase
import com.example.whitehelmettask.domain.usecase.GetMoviesUseCase
import com.example.whitehelmettask.domain.usecase.GetSearchSuggestionsUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideTmdbApi(): TmdbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: TmdbApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetMoviesUseCase(repository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetSearchSuggestionsUseCase(repository: MovieRepository): GetSearchSuggestionsUseCase {
        return GetSearchSuggestionsUseCase(repository)
    }

}
