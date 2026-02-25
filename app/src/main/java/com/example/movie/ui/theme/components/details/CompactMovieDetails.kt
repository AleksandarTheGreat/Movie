package com.example.movie.ui.theme.components.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie.model.MovieDetails
import com.example.movie.ui.theme.MovieTheme
import java.util.Locale

@Composable
fun CompactMovieDetails(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails,
) {
    val context = LocalContext.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            if (movieDetails.adult)
                Text(
                    text = "18+",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .zIndex(2f)
                        .padding(all = 8.dp)
                        .background(color = Color.Red, shape = CircleShape)
                        .padding(all = 2.dp)
                        .align(
                            alignment = Alignment.TopStart
                        )
                )

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movieDetails.posterImageUrl())
                    .build(),
                contentDescription = "Some description in case movie details image doesn't load",
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 1f), // 100% opacity
                            )
                        )
                    )
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .align(alignment = Alignment.TopEnd),
            ) {
                Text(
                    text = "${movieDetails.runtime}",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )

                Text(
                    text = "minutes",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )

                Text(
                    text = "⭐ ${movieDetails.voteAverageRoundedTo1Decimal()}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(
                        alignment = Alignment.BottomStart
                    ),
            ) {
                Text(
                    text = movieDetails.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp)
                        .zIndex(2f),
                    textAlign = TextAlign.Start,
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp)
                        .horizontalScroll(rememberScrollState())
                ) {
                    movieDetails.listGenres.forEach { genre ->
                        SuggestionChip(
                            onClick = {
                                Toast.makeText(
                                    context,
                                    "Nothing happens",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            label = {
                                Text(
                                    text = genre.name,
                                    color = Color.White,
                                )
                            },
                            modifier = Modifier
                                .padding(end = 4.dp)
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(12.dp)
        )

        Text(
            text = "Overview",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        Text(
            text = movieDetails.overview,
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )


        Spacer(
            modifier = Modifier
                .height(12.dp)
        )

        Text(
            text = "Production Companies",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 12.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            movieDetails.listProductionCompanies.forEach { company ->
                Card(
                    onClick = {},
                    modifier = Modifier
                        .width(100.dp)
                        .height(50.dp)
                        .padding(end = 8.dp)
                ) {
                    if (company.logoUrl() != "") {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize(),
                            model = ImageRequest.Builder(context)
                                .data(company.logoUrl())
                                .crossfade(true)
                                .build(),
                            contentDescription = "Some content description goes here",
                            contentScale = ContentScale.Crop
                        )
                    }
                    else {
                        Text(
                            text = company.name,
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CompactMovieDetailsPreview() {

    MovieTheme {
        CompactMovieDetails(
            movieDetails = MovieDetails()
        )
    }

}