package com.example.movie.data.api

import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    private val API_KEY: String get() = "3621b97015d6f2bc8dad5031f853d514"

    @GET("movie/popular")
    suspend fun fetchMoviesResponse(
        @Query(value = "api_key") apiKey: String = API_KEY,
        @Query(value = "page") page: Int = 1,
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun fetchMovieDetailsResponse(
        @Path("id") id: Int,
        @Query(value = "api_key") apiKey: String = API_KEY,
    ): MovieDetails
}