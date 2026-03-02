package com.example.movie.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.Objects

@Serializable
class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val adult: Boolean?,
    @SerializedName(value = "release_date")
    val releaseDate: String?,
    @SerializedName(value = "vote_average")
    val voteAverage: Double?,
    @SerializedName(value = "vote_count")
    val voteCount: Int?,
    @SerializedName(value = "poster_path")
    val posterPath: String?,
) {

    fun posterImageUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        return "${baseUrl}${posterPath}"
    }

    fun readableReleaseDate(): String {
        return if (releaseDate.isNullOrEmpty()) "N/A" else {
            val parts = releaseDate.split("-")

            if (parts.isNotEmpty()) {
                val localDate: LocalDate = LocalDate.of(parts[0].toInt(), parts[1].toInt(), parts[2].toInt())
                val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEE dd MMM yyyy")
                return localDate.format(dateTimeFormatter)
            }

            return "N/A"
        }
    }

    fun voteAverageRoundedTo1Decimal(): String {
        var avg = String.format(Locale.ROOT, "%.1f",voteAverage)
        if (voteAverage == 0.0)
            avg = "0"

        return avg;
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