package com.example.movie.data.repository

import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MoviesResponse

interface IRestApi {
    suspend fun fetchPopularMoviesResponse(page: Int): MoviesResponse
    suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails
    suspend fun fetchSearchedMoviesResponse(query: String): MoviesResponse
}