package com.example.movie.ui.theme.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.data.model.ScreenHeightType
import com.example.movie.data.model.ScreenWidthType
import com.example.movie.ui.theme.components.home.EmptyMovies
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.components.home.ContentMovies
import com.example.movie.ui.theme.viewModel.ViewModelHome
import com.example.movie.ui.theme.viewModel.ViewModelHomeFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    navigateToScreenDetails: (id: Int) -> Unit,
    navigateToScreenFavorites: () -> Unit,
    viewModelHome: ViewModelHome = viewModel(factory = ViewModelHomeFactory(LocalContext.current)),
    screenWidthType: ScreenWidthType,
    screenHeightType: ScreenHeightType,
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Movie app")
                        Button(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            onClick = navigateToScreenFavorites,
                        ) {
                            Text(text = "Favorites")
                        }
                    }
                }
            )
        },
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
                ContentMovies(
                    movieStateList = movieStateList,
                    screenWidthType = screenWidthType,
                    screenHeightType = screenHeightType,
                    modifier = Modifier
                        .fillMaxSize(),
                    navigateToScreenDetails = navigateToScreenDetails
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
            navigateToScreenFavorites = {},
            screenWidthType = ScreenWidthType.NARROW,
            screenHeightType = ScreenHeightType.NARROW
        )
    }
}







