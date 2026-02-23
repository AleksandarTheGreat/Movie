package com.example.movie.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerializedName(value = "page")
    val page: Int,
    @SerializedName(value = "results")
    val list: List<Movie>
)