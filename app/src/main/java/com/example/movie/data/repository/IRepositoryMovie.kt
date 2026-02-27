package com.example.movie.data.repository

import com.example.movie.data.model.Movie
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.MoviesResponse
import kotlinx.coroutines.flow.Flow

interface IRepositoryMovie {
    suspend fun fetchPopularMoviesResponse(page: Int): MoviesResponse
    suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails
    suspend fun fetchSearchedMoviesResponse(query: String): MoviesResponse

    fun loadAllMovieFavorites(): Flow<List<MovieFavorite>>

    suspend fun insert(movieFavorite: MovieFavorite)
    suspend fun delete(movieFavorite: MovieFavorite)

    suspend fun exists(id: Int): Boolean

}