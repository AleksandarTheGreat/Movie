package com.example.movie.data.model

import com.example.movie.data.model.movieDetails.Genre
import com.example.movie.data.model.movieDetails.ProductionCompany
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Serializable
data class MovieDetails(
    val id: Int,
    val title: String?,
    val overview: String?,
    val status: String?,
    val adult: Boolean?,
    @SerializedName(value = "release_date")
    val releaseDate: String?,
    @SerializedName(value = "backdrop_path")
    val backdropPath: String?,
    @SerializedName(value = "poster_path")
    val posterPath: String?,
    @SerializedName(value = "runtime")
    val runtime: Int?,
    @SerializedName(value = "genres")
    val listGenres: List<Genre>?,
    @SerializedName(value = "production_companies")
    val listProductionCompanies: List<ProductionCompany>?,
    @SerializedName(value = "vote_average")
    val voteAverage: Double?,
    @SerializedName(value = "vote_count")
    val voteCount: Int?,

) {
    constructor() : this(
            id = 0,
            title = "Default",
            overview = "Default",
            status = "Default",
            adult = false,
            releaseDate = "",
            backdropPath = "",
            posterPath = "",
            runtime = 0,
            listGenres = emptyList<Genre>(),
            listProductionCompanies = emptyList<ProductionCompany>(),
            voteAverage = 0.0,
            voteCount = 0,
        )

    fun posterImageUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"

        if (backdropPath == null) return ""
        if (backdropPath.isNotEmpty())
            return "${baseUrl}${backdropPath}"

        if (posterPath == null) return ""
        if (posterPath.isNotEmpty())
            return "${baseUrl}${posterPath}"

        return ""
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

    fun voteAverageRoundedTo1Decimal(): String = String.format(Locale.ROOT, "%.1f", voteAverage)

    fun isEmptyMovieDetails(): Boolean = (id == 0 && title == "Default" && overview == "Default" && posterPath == ""
            && status == "Default" && releaseDate == "") && !adult!! && backdropPath == "" && runtime == 0 && listGenres?.isEmpty() == true && listProductionCompanies?.isEmpty() == true
            && voteAverage == 0.0 && voteCount == 0

}