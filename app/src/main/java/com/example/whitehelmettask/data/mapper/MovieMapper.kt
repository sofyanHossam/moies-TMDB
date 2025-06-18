package com.example.whitehelmettask.data.mapper

import com.example.whitehelmettask.data.dto.MovieDto
import com.example.whitehelmettask.domain.model.Movie

fun MovieDto.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath ?: "",
        releaseDate = releaseDate ?: "",
        isAdult = adult
    )
}