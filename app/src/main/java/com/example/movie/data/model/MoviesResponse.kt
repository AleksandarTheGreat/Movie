package com.example.movie.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    @SerializedName(value = "page")
    val page: Int,
    @SerializedName(value = "results")
    val list: List<Movie>
)