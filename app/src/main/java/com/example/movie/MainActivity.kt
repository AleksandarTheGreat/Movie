package com.example.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movie.data.model.ScreenHeightType
import com.example.movie.data.model.ScreenWidthType
import com.example.movie.data.repository.GlupaKlasa
import com.example.movie.ui.theme.MovieTheme
import com.example.movie.ui.theme.details.ScreenDetails
import com.example.movie.ui.theme.details.ScreenDetailsGraph
import com.example.movie.ui.theme.favorites.ScreenFavorites
import com.example.movie.ui.theme.favorites.ScreenFavoritesGraph
import com.example.movie.ui.theme.home.ScreenHome
import com.example.movie.ui.theme.home.ScreenHomeGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var glupaKlasa: GlupaKlasa

    @SuppressLint("ConfigurationScreenWidthHeight")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        glupaKlasa.printNesho()

        enableEdgeToEdge()
        setContent {
            MovieTheme {
                val navController = rememberNavController()

                val windowSizeClass = calculateWindowSizeClass(this)

                val screenWidthType = findOutScreenWidthType(windowSizeClass)
                val screenHeightType = findOutScreenHeightType(windowSizeClass)

                NavHost(navController, ScreenHomeGraph) {
                    composable<ScreenHomeGraph> {
                        ScreenHome(
                            screenWidthType = screenWidthType,
                            screenHeightType = screenHeightType,
                            navigateToScreenDetails = { id ->
                                navController.navigate(ScreenDetailsGraph(id = id))
                            },
                            navigateToScreenFavorites = {
                                navController.navigate(ScreenFavoritesGraph)
                            }
                        )
                    }

                    composable<ScreenDetailsGraph> { backStackEntry ->
                        val route = backStackEntry.toRoute<ScreenDetailsGraph>()
                        val id = route.id

                        ScreenDetails(
                            screenWidthType = screenWidthType,
                            screenHeightType = screenHeightType,
                            navigateUp = {
                                navController.navigateUp()
                            },
                            movieId = id,
                        )
                    }

                    composable<ScreenFavoritesGraph> {
                        ScreenFavorites(
                            screenWidthType = screenWidthType,
                            screenHeightType = screenHeightType,
                            navigateUp = {
                                navController.navigateUp()
                            },
                            navigateToScreenDetails = { id ->
                                navController.navigate(ScreenDetailsGraph(id = id))
                            }
                        )
                    }
                }
            }
        }
    }

    fun findOutScreenWidthType(windowSizeClass: WindowSizeClass): ScreenWidthType {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            ScreenWidthType.NARROW
        } else {
            ScreenWidthType.WIDE
        }
    }

    fun findOutScreenHeightType(windowSizeClass: WindowSizeClass): ScreenHeightType {
        return if (windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact) {
            ScreenHeightType.SMALL
        } else {
            ScreenHeightType.BIG
        }
    }
}

















