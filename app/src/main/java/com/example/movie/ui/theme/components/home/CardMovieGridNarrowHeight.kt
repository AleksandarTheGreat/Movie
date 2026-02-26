package com.example.movie.ui.theme.components.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.movie.data.model.Movie
import com.example.movie.ui.theme.MovieTheme

@SuppressLint("DefaultLocale")
@Composable
fun CardMovieGridNarrowHeight(
    modifier: Modifier = Modifier,
    movie: Movie,
    navigateToScreenDetails: (id: Int) -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = {
            navigateToScreenDetails(movie.id)
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageAnd18Banner(movie)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 1f), // 30% opacity
                            )
                        )
                    )
                    .zIndex(1f)
            )

            MainTextContentColumn(movie)
        }
    }
}

@Composable
private fun MainTextContentColumn(movie: Movie) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .zIndex(2f)
    ) {
        Text(
            text = movie.title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "⭐ ${movie.voteAverageRoundedTo1Decimal()}",
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "\uD83D\uDCC6 ${movie.readableReleaseDate()}",
            fontSize = 12.sp,
            color = Color.White,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
private fun ImageAnd18Banner(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        if (movie.adult)
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
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterImageUrl())
                .build(),
            contentDescription = "Description of a single image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .zIndex(1f)
                .fillMaxSize()
        )
    }
}

@Preview
@Composable
private fun CardMovieGridNarrowHeightPreview() {
    MovieTheme {
        CardMovieGridNarrowHeight(
            movie = Movie(
                0, "Preview", "Preview", false, "Preview", 0.0, 0, "Preview"
            ),
            navigateToScreenDetails = {}
        )
    }
}