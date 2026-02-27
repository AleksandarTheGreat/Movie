package com.example.movie.ui.theme.home

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.MovieApp
import com.example.movie.R
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
    viewModelHome: ViewModelHome = viewModel(
        factory = ViewModelHomeFactory(
            repositoryMovie = (LocalContext.current.applicationContext as MovieApp).repositoryMovie
        )
    ),
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
                        OutlinedButton(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            onClick = navigateToScreenFavorites,
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_favorite_border),
                                contentDescription = "Some favorite content description"
                            )
                            Spacer(Modifier.width(8.dp))
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

            SearchBarCustom(
                viewModelHome = viewModelHome,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 16.dp)
            )

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

@Composable
fun SearchBarCustom(
    modifier: Modifier = Modifier,
    viewModelHome: ViewModelHome,
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { newValue ->
            query = newValue

            if (query.isNotEmpty())
                viewModelHome.fetchSearchedMovies(query)
            else
                viewModelHome.fetchPopularMovies()

            Log.d("Tag", "Fetching for ${query}")
        },
        placeholder = { Text("Search movie...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Nothing") },
        trailingIcon = {
            if (query.isNotEmpty()){
                IconButton(
                    onClick = { query = "" }
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Nothing again"
                    )
                }
            }
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Blue.copy(0.2f),
            unfocusedTextColor = Color.Gray.copy(0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        modifier = modifier,
    )
}







