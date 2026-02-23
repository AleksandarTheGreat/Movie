package com.example.movie.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.ui.theme.components.home.CardMovie
import com.example.movie.ui.theme.components.home.EmptyMovies
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.components.home.ListMovies
import com.example.movie.viewModel.ViewModelHome

@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    navigateToScreenDetails: () -> Unit,
    viewModelHome: ViewModelHome = viewModel(),
    screenHeight: Int,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val movieStateList by viewModelHome.immutableStateFlowMovies.collectAsStateWithLifecycle()

            if (!movieStateList.isEmpty()) {
                ListMovies(
                    screenHeight = screenHeight,
                    movieStateList = movieStateList,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            } else {
                EmptyMovies(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 36.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ScreenHomePreview() {
    MovieTheme {
        ScreenHome(
            navigateToScreenDetails = {},
            screenHeight = 0
        )
    }
}







