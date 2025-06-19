package com.example.whitehelmettask.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("popcorn_popping.json"))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = 1, // Play once
        speed = 1.2f
    )

    // Navigate after animation finishes or after fixed time
    LaunchedEffect(progress) {
        if (progress == 1f) {
            delay(500) // short delay after animation
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true } // Clear splash from back stack
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
