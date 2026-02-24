package com.example.movie.viewModel

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import com.example.movie.model.MovieDetails
import com.example.movie.repository.Implementations.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class ViewModelDetails(
    private val repositoryMovie: RepositoryMovie = RepositoryMovie()
) : ViewModel() {

    private val mutableStateFlowMovieDetails: MutableStateFlow<MovieDetails> = MutableStateFlow(MovieDetails())
    val immutableStateFlowMovieDetails = mutableStateFlowMovieDetails.asStateFlow()

    init {
        Log.d("Tag", "ViewModelDetails initialized")
    }

    fun updateMutableStateFlowMovieDetailsValue(movieDetails: MovieDetails){
        mutableStateFlowMovieDetails.update { movieDetails }
    }

    suspend fun loadMovieDetails(id: Int) {
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
}