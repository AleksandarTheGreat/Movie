package com.example.movie.repository

import com.example.movie.model.Movie
import com.example.movie.model.MovieDetails
import com.example.movie.model.MoviesResponse

interface IRepositoryMovie {
    suspend fun fetchPopularMoviesResponse(): MoviesResponse
    suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails

}