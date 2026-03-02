package com.example.movie.data.repository.Implementations

import android.content.Context
import com.example.movie.data.api.MovieApi
import com.example.movie.data.api.MovieModule
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.MoviesResponse
import com.example.movie.data.repository.IRestApi
import com.example.movie.data.repository.IRoomApi
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryMovie @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
): IRoomApi, IRestApi {

    override suspend fun fetchPopularMoviesResponse(page: Int): MoviesResponse {
        return movieApi.fetchMoviesResponse(page = page)
    }

    override suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails {
        return movieApi.fetchMovieDetailsResponse(id = id)
    }

    override suspend fun fetchSearchedMoviesResponse(query: String): MoviesResponse {
        return movieApi.fetchSearchedMoviesResponse(query = query)
    }

    override fun loadAllMovieFavorites(query: String): Flow<List<MovieFavorite>> {
        return movieDao.loadAllMovies(query = query)
    }

    override suspend fun insert(movieFavorite: MovieFavorite) {
        movieDao.insert(movieFavorite)
    }

    override suspend fun delete(movieFavorite: MovieFavorite) {
        movieDao.delete(movieFavorite)
    }

    override suspend fun exists(id: Int): Boolean {
        return movieDao.exists(id)
    }

}