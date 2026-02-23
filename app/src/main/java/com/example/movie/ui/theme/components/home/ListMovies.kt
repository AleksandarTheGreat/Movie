package com.example.movie.ui.theme.components.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.model.Movie
import com.example.movie.ui.theme.MovieTheme

@Composable
fun ListMovies(
    modifier: Modifier = Modifier,
    movieStateList: List<Movie>,
    screenHeight: Int,
) {
    val cardHeight = screenHeight / 6

    LazyColumn(
        modifier = modifier
    ) {
        items(movieStateList, key = { it.id }) { movie ->
            CardMovie(
                cardHeight = cardHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                movie = movie,
            )
        }
    }
}

@Preview
@Composable
private fun ListMoviesPreview() {
    MovieTheme {
        ListMovies(
            movieStateList = listOf(),
            screenHeight = 0,
        )
    }
}