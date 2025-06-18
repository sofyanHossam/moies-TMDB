package com.example.whitehelmettask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whitehelmettask.presentation.details.DetailsScreen
import com.example.whitehelmettask.presentation.home.HomeScreen
import com.example.whitehelmettask.ui.theme.WhiteHelmetTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // For light (white) colors
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(
//                android.graphics.Color.TRANSPARENT,
//                android.graphics.Color.TRANSPARENT
//            )
//        )

        super.onCreate(savedInstanceState)
        setContent {
            WhiteHelmetTaskTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(navController = navController)
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




