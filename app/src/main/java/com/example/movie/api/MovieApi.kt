package com.example.movie.api

import com.example.movie.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    private val API_KEY: String get() = "3621b97015d6f2bc8dad5031f853d514"

    @GET("movie/popular")
    suspend fun fetchMoviesResponse(
        @Query(value = "api_key") apiKey: String = API_KEY,
        @Query(value = "page") page: Int = 1,
    ): MoviesResponse

}