package com.example.movie.data.model.movieDetails

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Objects


@Serializable
class ProductionCompany(
    val id: Int,
    @SerializedName(value = "logo_path")
    val logoPath: String?,
    val name: String,
    @SerializedName(value = "origin_country")
    val originCountry: String,
) {

    fun logoUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        if (logoPath == null) return ""
        return "${baseUrl}${logoPath}"
    }

    override fun toString(): String {
        return "${id}, ${name}, ${logoPath}, ${originCountry}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductionCompany) return false

        return id == other.id
    }

    override fun hashCode(): Int = Objects.hash(id)

}