package com.example.movie.model

import com.example.movie.model.movieDetails.Genre
import com.example.movie.model.movieDetails.ProductionCompany
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
    @SerializedName(value = "genres")
    val listGenres: List<Genre>,
    @SerializedName(value = "production_companies")
    val listProductionCompanies: List<ProductionCompany>,
) {
    constructor() : this(
            id = 0,
            title = "Default",
            overview = "Default",
            status = "Default",
            releaseDate = "",
            backdropPath = "",
            runtime = 0,
            listGenres = emptyList<Genre>(),
            listProductionCompanies = emptyList<ProductionCompany>()
        )
}