package com.example.movie.repository.Implementations

import com.example.movie.api.MovieApi
import com.example.movie.api.MovieObject
import com.example.movie.model.Movie
import com.example.movie.model.MovieDetails
import com.example.movie.model.MoviesResponse
import com.example.movie.repository.IRepositoryMovie

class RepositoryMovie(
    private val movieApi: MovieApi = MovieObject.api
): IRepositoryMovie {

    override suspend fun fetchPopularMoviesResponse(): MoviesResponse {
        return movieApi.fetchMoviesResponse()
    }

    override suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails {
        return movieApi.fetchMovieDetailsResponse(id = id)
    }

}