package com.example.movie.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val status: String,
    @SerializedName(value = "release_date")
    val releaseDate: String,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String,
    @SerializedName(value = "runtime")
    val runtime: Int,
)