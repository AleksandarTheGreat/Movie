package com.example.movie.data.repository.Implementations

import android.content.Context
import com.example.movie.data.api.MovieApi
import com.example.movie.data.api.MovieObject
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.MoviesResponse
import com.example.movie.data.repository.IRepositoryMovie
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
import kotlinx.coroutines.flow.Flow

class RepositoryMovie(
    private val context: Context,
    private val movieApi: MovieApi = MovieObject.api,
    private val movieDao: MovieDao = AppDatabase.getInstance(context).movieDao()
): IRepositoryMovie {

    override suspend fun fetchPopularMoviesResponse(page: Int): MoviesResponse {
        return movieApi.fetchMoviesResponse(page = page)
    }

    override suspend fun fetchMovieDetailsResponse(id: Int): MovieDetails {
        return movieApi.fetchMovieDetailsResponse(id = id)
    }

    override suspend fun fetchSearchedMoviesResponse(query: String): MoviesResponse {
        return movieApi.fetchSearchedMoviesResponse(query = query)
    }

    override fun loadAllMovieFavorites(): Flow<List<MovieFavorite>> {
        return movieDao.loadAllMovies()
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