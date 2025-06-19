package com.example.whitehelmettask.data.mapper
import com.example.whitehelmettask.data.local.entity.MovieEntity
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

// From Entity (DB) to Domain
fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        isAdult = isAdult
    )
}

// From Domain to Entity (for saving in DB)
fun Movie.toEntity(query: String): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        posterPath = posterPath,
        releaseDate = releaseDate,
        isAdult = isAdult,
        query = query
    )
}

