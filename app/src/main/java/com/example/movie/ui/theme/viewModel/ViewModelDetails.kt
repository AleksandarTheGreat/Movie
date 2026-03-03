package com.example.movie.ui.theme.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.Movie
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.MoviesResponse
import com.example.movie.data.repository.Implementations.RepositoryMovie
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ViewModelDetails @Inject constructor(
    private val repositoryMovie: RepositoryMovie,
) : ViewModel() {

    private val mutableStateFlowMovieDetails: MutableStateFlow<MovieDetails> =
        MutableStateFlow(MovieDetails())
    val immutableStateFlowMovieDetails = mutableStateFlowMovieDetails.asStateFlow()

    private val mutableStateFlowMovieExists: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val immutableStateFlowMovieExists = mutableStateFlowMovieExists.asStateFlow()

    private val mutableStateFlowRelatedMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val immutableStateFlowRelatedMovies = mutableStateFlowRelatedMovies.asStateFlow()

    init {
        Log.d("Tag", "ViewModelDetails initialized ${this::hashCode}")
    }

    fun updateMutableStateFlowMovieDetailsValue(movieDetails: MovieDetails) {
        mutableStateFlowMovieDetails.update { movieDetails }
    }

    fun updateMutableStateFlowMovieExistsValue(exists: Boolean) {
        mutableStateFlowMovieExists.update { exists }
    }

    fun updateMutableStateFlowRelatedMovies(list: List<Movie>) {
        mutableStateFlowRelatedMovies.update { list }
    }

    fun fetchMovieDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetails = try {
                repositoryMovie.fetchMovieDetailsResponse(id = id)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                MovieDetails()
            }

            updateMutableStateFlowMovieDetailsValue(movieDetails)
            val listGenres = movieDetails.listGenres ?: emptyList()
            val listIds = listGenres.map { genre -> genre.id }

            if (listGenres.isNotEmpty()){
                fetchGenresMoviesResponse(
                    currentMovieId = id,
                    genres = listIds
                )
            }
        }
    }

    fun toggleFavorite(movieFavorite: MovieFavorite, isLikedState: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (isLikedState) {
                insertMovieFavorite(movieFavorite)
                updateMutableStateFlowMovieExistsValue(true)
            } else {
                deleteMovieFavorite(movieFavorite)
                updateMutableStateFlowMovieExistsValue(false)
            }
        }
    }

    suspend fun insertMovieFavorite(movieFavorite: MovieFavorite) {
        repositoryMovie.insert(movieFavorite)
    }

    suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite) {
        repositoryMovie.delete(movieFavorite)
    }

    fun checkIfMovieExistsInFavorites(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val exists = repositoryMovie.exists(id)
            updateMutableStateFlowMovieExistsValue(exists)
        }
    }

    fun fetchGenresMoviesResponse(currentMovieId: Int, genres: List<Int>) {
        viewModelScope.launch(Dispatchers.IO) {
            val listMovies = try {
                repositoryMovie.fetchGenresMoviesResponse(genres = genres).list.filter { movie -> movie.id != currentMovieId }
            } catch (e: Exception) {
                emptyList<Movie>()
            }

            updateMutableStateFlowRelatedMovies(listMovies)
        }
    }
}















