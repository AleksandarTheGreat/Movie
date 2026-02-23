package com.example.movie.ui.theme.components.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.request.ImageRequest
import coil3.request.AsyncImage
import com.example.movie.model.Movie
import com.example.movie.ui.theme.MovieTheme

@Composable
fun CardMovie(
    modifier: Modifier = Modifier,
    movie: Movie,
    cardHeight: Int,
) {
    Card(
        modifier = modifier,
        onClick = {},
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterImageUrl())
                    .build(),
                contentDescription = "Description of a single image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight.dp)
                    .weight(1f)
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight.dp)
                    .padding(vertical = 6.dp, horizontal = 12.dp)
                    .weight(2f)
            ) {
                Text(
                    text = movie.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.height(4.dp))

                if (movie.overview.isNotEmpty())
                    Text(
                        text = movie.overview,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 2,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                    )
                else
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "No overview :(",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )
                    }
            }
        }
    }
}

@Composable
fun AsyncImage(
    model: ImageRequest,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
) {
    TODO("Not yet implemented")
}

@Preview
@Composable
private fun CardMoviePreview() {
    MovieTheme {
        CardMovie(
            cardHeight = 0,
            movie = Movie(
                0, "Preview", "Preview", false, "Preview", 0.0, 0, "Preview"
            )
        )
    }
}