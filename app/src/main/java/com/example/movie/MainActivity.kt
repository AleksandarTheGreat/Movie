package com.example.movie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movie.api.MovieObject
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.details.ScreenDetails
import com.example.movie.ui.theme.details.ScreenDetailsGraph
import com.example.movie.ui.theme.home.ScreenHome
import com.example.movie.ui.theme.home.ScreenHomeGraph
import com.example.movie.viewModel.ViewModelDetails
import com.example.movie.viewModel.ViewModelHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @SuppressLint("ConfigurationScreenWidthHeight")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MovieTheme {
                val navController = rememberNavController()
                val screenHeight: Int = LocalConfiguration.current.screenHeightDp

                NavHost(navController, ScreenHomeGraph) {
                    composable<ScreenHomeGraph> {
                        ScreenHome(
                            screenHeight = screenHeight,
                            navigateToScreenDetails = {
                                navController.navigate(ScreenDetailsGraph)
                            }
                        )
                    }

                    composable<ScreenDetailsGraph> {
                        ScreenDetails(
                            navigateUp = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

















