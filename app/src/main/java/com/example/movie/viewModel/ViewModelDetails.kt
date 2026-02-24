package com.example.movie.viewModel

import android.util.Log
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.lifecycle.ViewModel
import com.example.movie.repository.Implementations.RepositoryMovie

class ViewModelDetails(
    private val repositoryMovie: RepositoryMovie = RepositoryMovie()
) : ViewModel() {

    init {
        Log.d("Tag", "ViewModelDetails initialized")
    }

}