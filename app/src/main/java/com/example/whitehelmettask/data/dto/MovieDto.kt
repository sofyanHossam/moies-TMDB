package com.example.whitehelmettask.data.dto

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val results: List<MovieDto>
)

data class MovieDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("adult") val adult: Boolean
)