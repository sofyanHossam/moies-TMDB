package com.example.whitehelmettask.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val isAdult: Boolean
)
