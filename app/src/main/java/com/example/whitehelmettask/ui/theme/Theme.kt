package com.example.whitehelmettask.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Secondary,
    onSecondary = OnSecondary,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    error = Error,
    onError = OnError
)
private val DarkColors = darkColorScheme(
    primary = PrimaryLight,
    onPrimary = Color.Black,
    secondary = Secondary,
    onSecondary = Color.Black,
    background = Color(0xFF121212),
    surface = Color(0xFF1C1C1C),
    onSurface = Color.White,
    error = Error,
    onError = Color.White
)


@Composable
fun WhiteHelmetTaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    val activity = view.context as Activity

    SideEffect {
        WindowCompat.getInsetsController(activity.window, view).isAppearanceLightStatusBars = !darkTheme
        activity.window.statusBarColor = android.graphics.Color.TRANSPARENT
    }

    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

