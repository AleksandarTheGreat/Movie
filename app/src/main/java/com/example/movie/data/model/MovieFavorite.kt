package com.example.movie.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.util.TableInfo

@Entity
data class MovieFavorite(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_path")
    val posterPath: String,
) {
    fun posterImageUrl(): String {
        val baseUrl = "https://image.tmdb.org/t/p/w500"
        return "${baseUrl}${posterPath}"
    }
}