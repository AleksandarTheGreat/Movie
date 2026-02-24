package com.example.movie.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.model.Movie
import com.example.movie.repository.Implementations.RepositoryMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelHome(
    private val repositoryMovie: RepositoryMovie = RepositoryMovie()
) : ViewModel() {

    private val mutableStateFlowMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val immutableStateFlowMovies = mutableStateFlowMovies.asStateFlow()

    init {
        viewModelScope.launch { loadAll() }
        Log.d("Tag", "ViewModelHome initialized")
    }

    fun updateMutableStateFlowMovies(list: List<Movie>) {
        mutableStateFlowMovies.update { list }
    }

    suspend fun loadAll() {
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