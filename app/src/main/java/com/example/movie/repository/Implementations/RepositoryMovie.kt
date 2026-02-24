package com.example.movie.repository.Implementations

import com.example.movie.api.MovieApi
import com.example.movie.api.MovieObject
import com.example.movie.model.Movie
import com.example.movie.model.MoviesResponse
import com.example.movie.repository.IRepositoryMovie

class RepositoryMovie(
    private val movieApi: MovieApi = MovieObject.api
): IRepositoryMovie {

    override suspend fun fetchPopularMoviesResponse(): MoviesResponse {
        return movieApi.fetchMoviesResponse()
    }

}