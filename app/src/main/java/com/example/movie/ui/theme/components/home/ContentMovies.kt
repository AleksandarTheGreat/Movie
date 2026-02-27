package com.example.movie.ui.theme.components.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movie.data.model.Movie
import com.example.movie.data.model.ScreenHeightType
import com.example.movie.data.model.ScreenWidthType
import com.example.movie.ui.theme.MovieTheme

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ContentMovies(
    modifier: Modifier = Modifier,
    movieStateList: List<Movie>,
    screenWidthType: ScreenWidthType,
    screenHeightType: ScreenHeightType,
    navigateToScreenDetails: (id: Int) -> Unit,
) {

    if (screenWidthType == ScreenWidthType.NARROW) {

        val context = LocalContext.current
        val cardHeight = LocalConfiguration.current.screenHeightDp / 6
        val listState = rememberLazyListState()

        val isAtBottom by remember {
            derivedStateOf {
                val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                lastVisible == movieStateList.size - 1
            }
        }

        LaunchedEffect(isAtBottom) {
            Toast.makeText(context, "Reached the bottom", Toast.LENGTH_SHORT).show()
        }

        LazyColumn(
            state = listState,
            modifier = modifier
        ) {
            items(movieStateList, key = { it.id }) { movie ->
                CardMovieNarrowWidth(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight.dp)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    movie = movie,
                    navigateToScreenDetails = navigateToScreenDetails,
                )
            }
        }
    } else {
        // calculate height as well
        val cardWidth = LocalConfiguration.current.screenWidthDp / 3

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = modifier
        ) {
            if (screenHeightType == ScreenHeightType.BIG) {
                items(movieStateList, key = { it.id }) { movie ->
                    CardMovieGridBigHeight(
                        modifier = Modifier
                            .width(cardWidth.dp)
                            .fillMaxHeight()
                            .padding(all = 4.dp),
                        movie = movie,
                        navigateToScreenDetails = navigateToScreenDetails
                    )
                }
            } else {
                items(movieStateList, key = { it.id }) { movie ->
                    CardMovieGridSmallHeight(
                        modifier = Modifier
                            .width(cardWidth.dp)
                            .fillMaxHeight()
                            .padding(all = 4.dp),
                        movie = movie,
                        navigateToScreenDetails = navigateToScreenDetails
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContentMoviesPreview() {
    MovieTheme {
        ContentMovies(
            movieStateList = listOf(),
            screenWidthType = ScreenWidthType.NARROW,
            screenHeightType = ScreenHeightType.SMALL,
            navigateToScreenDetails = {}
        )
    }
}