package com.example.whitehelmettask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whitehelmettask.data.local.ThemePreference
import com.example.whitehelmettask.presentation.details.DetailsScreen
import com.example.whitehelmettask.presentation.home.HomeScreen
import com.example.whitehelmettask.ui.theme.WhiteHelmetTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        super.onCreate(savedInstanceState)

        val themePref = ThemePreference(this)

        setContent {
            var isDarkTheme by rememberSaveable { mutableStateOf(themePref.isDarkMode()) }

            WhiteHelmetTaskTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(
                                navController = navController,
                                isDarkTheme = isDarkTheme,
                                onToggleTheme = {
                                    isDarkTheme = !isDarkTheme
                                    themePref.setDarkMode(isDarkTheme)
                                }
                            )
                        }
                        composable("details/{movieId}") { backStackEntry ->
                            val movieId = backStackEntry.arguments?.getString("movieId")!!.toInt()
                            DetailsScreen(
                                navController = navController,
                                movieId = movieId,
                            )
                        }
                    }
                }
            }
        }
    }
}
