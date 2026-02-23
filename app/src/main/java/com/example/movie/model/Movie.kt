package com.example.movie.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Objects

@Serializable
class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    @SerializedName(value = "release_date")
    val releaseDate: String,
    @SerializedName(value = "vote_average")
    val voteAverage: Double,
    @SerializedName(value = "vote_count")
    val voteCount: Int,
    @SerializedName(value = "poster_path")
    val posterPath: String,
) {

    fun posterImageUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        return "${baseUrl}${posterPath}"
    }

    override fun toString(): String {
        return "${id}, ${title}, ${overview}, ${adult}, ${releaseDate}, ${voteAverage}, ${voteCount}, ${posterPath}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false

        return id == other.id
    }

    override fun hashCode(): Int = Objects.hash(id)

}