package com.example.movie.ui.theme.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.data.repository.Implementations.RepositoryMovie

@Suppress("UNCHECKED_CAST")
class ViewModelFavoritesFactory(
    private val repositoryMovie: RepositoryMovie,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelFavorites(repositoryMovie) as T
    }
}