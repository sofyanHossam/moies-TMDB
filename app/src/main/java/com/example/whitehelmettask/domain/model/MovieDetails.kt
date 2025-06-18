package com.example.whitehelmettask.domain.model

data class MovieDetails(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val adult: Boolean,
    val tagline: String,
    val originCountry: List<String>,
    val genres: List<String>
)
