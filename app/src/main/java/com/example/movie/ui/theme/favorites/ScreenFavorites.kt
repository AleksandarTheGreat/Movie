package com.example.movie.ui.theme.favorites

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.movie.MovieApp
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.ScreenHeightType
import com.example.movie.data.model.ScreenWidthType
import com.example.movie.data.repository.Implementations.RepositoryMovie
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.components.favorites.EmptyMovieFavorites
import com.example.movie.ui.theme.viewModel.ViewModelFavorites
import com.example.movie.ui.theme.viewModel.ViewModelFavoritesFactory
import com.example.movie.ui.theme.viewModel.ViewModelHome

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenFavorites(
    modifier: Modifier = Modifier,
    screenWidthType: ScreenWidthType,
    screenHeightType: ScreenHeightType,
    navigateUp: () -> Unit,
    navigateToScreenDetails: (id: Int) -> Unit,
    viewModelFavorites: ViewModelFavorites = viewModel(
        factory = ViewModelFavoritesFactory(
            repositoryMovie = (LocalContext.current.applicationContext as MovieApp).repositoryMovie
        )
    )
) {
    val context = LocalContext.current
    val movieFavorites by viewModelFavorites.movieFavoritesStateFlow.collectAsStateWithLifecycle()
    Toast.makeText(context, "Loaded ${movieFavorites.size} favorites", Toast.LENGTH_SHORT).show()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Screen Favorites")
                },
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Icon go back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            SearchBarCustom(
                viewModelFavorites = viewModelFavorites,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 16.dp),
            )

            if (movieFavorites.isNotEmpty()) {
                ContentMovieFavorites(
                    modifier = Modifier
                        .fillMaxSize(),
                    movieFavorites = movieFavorites,
                    context = context,
                    navigateToScreenDetails = navigateToScreenDetails,
                )
            } else {
                EmptyMovieFavorites(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                        .padding(all = 36.dp)
                )
            }
        }
    }
}

@Composable
private fun ContentMovieFavorites(
    modifier: Modifier,
    movieFavorites: List<MovieFavorite>,
    context: Context,
    navigateToScreenDetails: (Int) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        items(movieFavorites, key = { it.id }) { movieFavorite ->
            CardMovieFavorite(
                context = context,
                movieFavorite = movieFavorite,
                navigateToScreenDetails = navigateToScreenDetails
            )
        }

    }
}

@Composable
private fun CardMovieFavorite(
    context: Context,
    movieFavorite: MovieFavorite,
    navigateToScreenDetails: (id: Int) -> Unit,
) {
    Card(
        onClick = {
            navigateToScreenDetails(movieFavorite.id)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageAndGradient(movieFavorite)

            ColumnTitleAndOverview(movieFavorite)
        }
    }
}

@Composable
private fun ColumnTitleAndOverview(movieFavorite: MovieFavorite) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        Text(
            text = movieFavorite.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Text(
            text = movieFavorite.overview,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            maxLines = 2,
        )
    }
}

@Composable
private fun ImageAndGradient(movieFavorite: MovieFavorite) {
    AsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = movieFavorite.posterImageUrl(),
        contentDescription = "Description of this poster image for movie favorite",
        contentScale = ContentScale.Crop,
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Black.copy(0.9f)
                    )
                )
            )
    )
}

@Composable
fun SearchBarCustom(
    modifier: Modifier = Modifier,
    viewModelFavorites: ViewModelFavorites,
) {
    var query by remember { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { newValue ->
            query = newValue
            viewModelFavorites.search(query)

            Log.d("Tag", "Fetching locally for '${query}'")
        },
        placeholder = { Text("Search movie...") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Nothing") },
        trailingIcon = {
            if (query.isNotEmpty()) {
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
            focusedContainerColor = Color.Red.copy(0.2f),
            unfocusedTextColor = Color.Gray.copy(0.3f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        modifier = modifier,
    )
}


@Preview
@Composable
private fun ScreenFavoritesPreview() {
    MovieTheme {
        ScreenFavorites(
            screenWidthType = ScreenWidthType.NARROW,
            screenHeightType = ScreenHeightType.SMALL,
            navigateToScreenDetails = {},
            navigateUp = {}
        )
    }
}
