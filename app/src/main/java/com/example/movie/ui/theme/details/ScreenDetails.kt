package com.example.movie.ui.theme.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movie.model.ScreenWidthType
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.viewModel.ViewModelDetails
import java.util.concurrent.CountDownLatch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDetails(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModelDetails: ViewModelDetails = viewModel(),
    screenWidthType: ScreenWidthType,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Screen Details") },
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp,
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Icon description"
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Screen Details",
            )
        }
    }
}

@Preview
@Composable
private fun ScreenDetailsPreview() {
    MovieTheme {
        ScreenDetails(
            navigateUp = {},
            screenWidthType = ScreenWidthType.NARROW,
        )
    }
}









