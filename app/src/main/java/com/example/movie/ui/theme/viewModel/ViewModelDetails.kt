package com.example.movie.ui.theme.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.repository.Implementations.RepositoryMovie
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
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

class ViewModelDetails(
    private val context: Context,
    private val repositoryMovie: RepositoryMovie = RepositoryMovie(context),
) : ViewModel() {

    private val mutableStateFlowMovieDetails: MutableStateFlow<MovieDetails> = MutableStateFlow(MovieDetails())
    val immutableStateFlowMovieDetails = mutableStateFlowMovieDetails.asStateFlow()

    init {
        Log.d("Tag", "ViewModelDetails initialized")
    }

    fun updateMutableStateFlowMovieDetailsValue(movieDetails: MovieDetails){
        mutableStateFlowMovieDetails.update { movieDetails }
    }

    suspend fun fetchMovieDetails(id: Int) {
        withContext(Dispatchers.IO) {
            val movieDetails = try {
                repositoryMovie.fetchMovieDetailsResponse(id = id)
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                MovieDetails()
            }

            updateMutableStateFlowMovieDetailsValue(movieDetails)
        }
    }

    fun toggleFavorite(movieFavorite: MovieFavorite, isLikedState: Boolean) {
        viewModelScope.launch {
            if (isLikedState) {
                insertMovieFavorite(movieFavorite)
            } else {
                deleteMovieFavorite(movieFavorite)
            }
        }
    }

    suspend fun insertMovieFavorite(movieFavorite: MovieFavorite) {
        withContext(Dispatchers.IO) {
            repositoryMovie.insert(movieFavorite)
        }
    }

    suspend fun deleteMovieFavorite(movieFavorite: MovieFavorite) {
        withContext(Dispatchers.IO) {
            repositoryMovie.delete(movieFavorite)
        }
    }
}















