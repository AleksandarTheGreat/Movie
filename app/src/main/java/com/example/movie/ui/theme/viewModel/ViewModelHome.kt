package com.example.movie.ui.theme.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.MovieApp
import com.example.movie.data.model.Movie
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.repository.Implementations.RepositoryMovie
import com.example.movie.data.room.AppDatabase
import com.example.movie.data.room.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelHome(
    private val repositoryMovie: RepositoryMovie,
) : ViewModel() {

    private val mutableStateFlowMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val immutableStateFlowMovies = mutableStateFlowMovies.asStateFlow()

    init {
        viewModelScope.launch { fetchPopularMovies() }
        Log.d("Tag", "ViewModelHome initialized")
    }

    fun updateMutableStateFlowMovies(list: List<Movie>) {
        mutableStateFlowMovies.update { list }
    }

    suspend fun fetchPopularMovies() {
        withContext(Dispatchers.IO) {
            val list = try {
                repositoryMovie.fetchPopularMoviesResponse().list
            } catch (e: Exception) {
                Log.d("Error" , e.toString())
                emptyList<Movie>()
            }

            updateMutableStateFlowMovies(list)
        }
    }

}