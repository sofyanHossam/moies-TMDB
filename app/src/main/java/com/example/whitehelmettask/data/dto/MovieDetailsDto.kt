package com.example.whitehelmettask.data.dto

data class MovieDetailsDto(
    val id: Int,
    val title: String,
    val original_title: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val overview: String,
    val release_date: String,
    val vote_average: Double,
    val adult: Boolean,
    val tagline: String,
    val origin_country: List<String>,
    val genres: List<GenreDto>
)

data class GenreDto(
    val id: Int,
    val name: String
)
