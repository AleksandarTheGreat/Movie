package com.example.movie.ui.theme.components.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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
fun CardMovieNarrowWidth(
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
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
        ) {
            ImageAnd18Banner(movie)

            ColumnMainContentOfCard(movie)
        }
    }
}

@Composable
private fun RowScope.ColumnMainContentOfCard(movie: Movie) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
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
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )
        else
            Text(
                text = "No overview :(",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {


            Text(
                text = "⭐ ${movie.voteAverageRoundedTo1Decimal()}",
                fontSize = 14.sp
            )
            Text(
                text = "\uD83D\uDCC6 ${movie.readableReleaseDate()}",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun RowScope.ImageAnd18Banner(movie: Movie) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
    ) {

        if (movie.adult)
            Text(
                text = "18+",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .zIndex(2f)
                    .padding(all = 4.dp)
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
private fun CardMovieNarrowWidthPreview() {
    MovieTheme {
        CardMovieNarrowWidth(
            movie = Movie(
                0, "Preview", "Preview", false, "Preview", 0.0, 0, "Preview"
            ),
            navigateToScreenDetails = {},
        )
    }
}