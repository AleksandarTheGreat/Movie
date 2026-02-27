package com.example.movie.ui.theme.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.repository.Implementations.RepositoryMovie
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ViewModelFavorites(
    private val repositoryMovie: RepositoryMovie
) : ViewModel() {

    val movieFavoritesStateFlow: StateFlow<List<MovieFavorite>> = repositoryMovie.loadAllMovieFavorites().stateIn(viewModelScope,
        SharingStarted.WhileSubscribed(), emptyList())

    init {
        Log.d("Tag", "ViewModelFavorites initialized")
    }

}