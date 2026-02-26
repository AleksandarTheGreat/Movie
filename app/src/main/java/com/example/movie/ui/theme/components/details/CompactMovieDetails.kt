package com.example.movie.ui.theme.components.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movie.R
import com.example.movie.data.model.MovieDetails
import com.example.movie.data.model.MovieFavorite
import com.example.movie.data.model.movieDetails.ProductionCompany
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.viewModel.ViewModelDetails
import com.example.movie.ui.theme.viewModel.ViewModelDetailsFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CompactMovieDetails(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails,
    viewModelDetails: ViewModelDetails,
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModelDetails.checkIfMovieExistsInFavorites(movieDetails.id)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            PosterImageWithBlackShadowAnd18Banner(movieDetails)

            DetailsColumn(movieDetails)

            TitleAndGenresColumn(movieDetails, context)
        }

        Spacer(
            modifier = Modifier
                .height(12.dp)
        )

        HeaderOverviewWithHeart(
            subtitle = "Overview",
            movieDetails = movieDetails,
            viewModelDetails = viewModelDetails,
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

        HeaderForDetailsSubtitle(
            subtitle = "Production Companies"
        )

        LazyRowProductionCompanies(movieDetails)
    }
}

@Composable
private fun HeaderOverviewWithHeart(subtitle: String, movieDetails: MovieDetails, viewModelDetails: ViewModelDetails) {

    val context = LocalContext.current
    val movieExistsState by viewModelDetails.immutableStateFlowMovieExists.collectAsStateWithLifecycle()
    var movieExists = movieExistsState

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = subtitle,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
        )

        val imageRes = if (movieExistsState) R.drawable.ic_favorite_filled
        else R.drawable.ic_favorite_border

        Image(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clickable {
                    movieExists = !movieExists
                    val movieFavorite = MovieFavorite(
                        id = movieDetails.id,
                        title = movieDetails.title,
                        overview = movieDetails.overview,
                        posterPath = movieDetails.posterPath ?: ""
                    )

                    if (movieExists) { Toast.makeText(context, "'${movieDetails.title}' added to favorites", Toast.LENGTH_SHORT).show() }
                    else { Toast.makeText(context, "'${movieDetails.title}' removed from favorites", Toast.LENGTH_SHORT).show() }

                    viewModelDetails.toggleFavorite(movieFavorite, movieExists)
                },
            painter = painterResource(imageRes),
            contentDescription = "Heart image description and stuff"
        )
    }
}

@Composable
private fun BoxScope.PosterImageWithBlackShadowAnd18Banner(movieDetails: MovieDetails) {
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
}

@Composable
private fun LazyRowProductionCompanies(movieDetails: MovieDetails) {
    LazyRow(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 12.dp)
    ) {
        items(movieDetails.listProductionCompanies) { company ->
            ProductionCompanyCard(company)
        }
    }
}

@Composable
private fun ProductionCompanyCard(company: ProductionCompany) {
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
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                model = company.logoUrl(),
                contentDescription = "Some content description goes here",
                contentScale = ContentScale.Fit
            )
        } else {
            Text(
                text = company.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun HeaderForDetailsSubtitle(subtitle: String) {
    Text(
        text = subtitle,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
    )
}

@Composable
private fun BoxScope.TitleAndGenresColumn(
    movieDetails: MovieDetails,
    context: Context
) {
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

        LazyRow (
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp)
        ) {
            items(movieDetails.listGenres) { genre ->
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

@Composable
private fun BoxScope.DetailsColumn(movieDetails: MovieDetails) {
    Column(
        horizontalAlignment = Alignment.End,
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

        Text(
            text = "${movieDetails.voteCount} votes",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )

        Text(
            text = "Released on",
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
        )

        Text(
            text = movieDetails.readableReleaseDate(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Preview
@Composable
private fun CompactMovieDetailsPreview() {

    MovieTheme {
        CompactMovieDetails(
            movieDetails = MovieDetails(),
            viewModelDetails = viewModel(factory = ViewModelDetailsFactory(context = LocalContext.current))
        )
    }

}