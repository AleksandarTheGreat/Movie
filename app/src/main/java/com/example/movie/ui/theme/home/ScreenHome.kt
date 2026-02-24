package com.example.movie.ui.theme.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.model.ScreenWidthType
import com.example.movie.ui.theme.components.home.CardMovie
import com.example.movie.ui.theme.components.home.EmptyMovies
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.components.home.ListMovies
import com.example.movie.viewModel.ViewModelHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    navigateToScreenDetails: () -> Unit,
    viewModelHome: ViewModelHome = viewModel(),
    screenWidthType: ScreenWidthType,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie app")
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val movieStateList by viewModelHome.immutableStateFlowMovies.collectAsStateWithLifecycle()
            // If window width is medium or expanded
            // show a grid layout 2x2 horizontally

            if (!movieStateList.isEmpty()) {
                ListMovies(
                    movieStateList = movieStateList,
                    screenWidthType = screenWidthType,
                    modifier = Modifier
                        .fillMaxSize(),
                )
            } else {
                EmptyMovies(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
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
            screenWidthType = ScreenWidthType.NARROW
        )
    }
}







