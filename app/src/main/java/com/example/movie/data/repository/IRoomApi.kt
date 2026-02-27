package com.example.movie.data.repository

import com.example.movie.data.model.MovieFavorite
import kotlinx.coroutines.flow.Flow

interface IRoomApi {

    fun loadAllMovieFavorites(query: String): Flow<List<MovieFavorite>>
    suspend fun insert(movieFavorite: MovieFavorite)
    suspend fun delete(movieFavorite: MovieFavorite)
    suspend fun exists(id: Int): Boolean

}