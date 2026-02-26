package com.example.movie.ui.theme.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewModelFavoritesFactory(
    private val context: Context,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelFavorites(context) as T
    }
}