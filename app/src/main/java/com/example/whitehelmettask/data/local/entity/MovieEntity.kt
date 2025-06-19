package com.example.whitehelmettask.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val isAdult: Boolean,
    val query: String // used to associate this movie with a search query
)
