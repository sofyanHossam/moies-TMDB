package com.example.whitehelmettask.data.mapper

import com.example.whitehelmettask.data.dto.MovieDetailsDto
import com.example.whitehelmettask.domain.model.MovieDetails

fun MovieDetailsDto.toDomain(): MovieDetails {
    return MovieDetails(
        id = id,
        title = title,
        posterPath = poster_path,
        backdropPath = backdrop_path,
        overview = overview,
        releaseDate = release_date,
        voteAverage = vote_average,
        adult = adult,
        tagline = tagline,
        originCountry = origin_country,
        genres = genres.map { it.name }
    )
}
