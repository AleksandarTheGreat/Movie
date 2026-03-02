package com.example.movie.ui.theme.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.repository.Implementations.RepositoryMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ViewModelFavorites @Inject constructor(
    private val repositoryMovie: RepositoryMovie
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val movieFavoritesStateFlow = searchQuery
        .flatMapLatest { query ->
            repositoryMovie.loadAllMovieFavorites(query)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

//    val movieFavoritesStateFlow: StateFlow<List<MovieFavorite>> = repositoryMovie.loadAllMovieFavorites(query = "").stateIn(viewModelScope,
//        SharingStarted.WhileSubscribed(), emptyList())

    init {
        Log.d("Tag", "ViewModelFavorites initialized")
    }

    fun search(query: String) {
        searchQuery.value = query
    }
}