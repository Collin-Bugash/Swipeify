package com.collinbugash.swipeify.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

//USING THIS ONE AT THE MOMENT
private val DarkColorScheme = darkColorScheme(
    primary = PrimaryLightGreen300,
    onPrimary = BackgroundBlack800,
    primaryContainer = PrimaryLightGreen600,
    onPrimaryContainer = PrimaryLightGreen50,

    secondary = SecondaryDirtyGreen500,
    onSecondary = BackgroundBlack800,
    secondaryContainer = SecondaryDirtyGreen500,
    onSecondaryContainer = SecondaryDirtyGreen200,

    tertiary = TertiaryLightPurple400,
    onTertiary = BackgroundBlack800,
    tertiaryContainer = TertiaryLightPurple400,
    onTertiaryContainer = TertiaryLightPurple50,

    background = BackgroundBlack900,
    onBackground = Color.White,
    surface = BackgroundBlack800,
    onSurface = BackgroundBlack300,

    outline = BackgroundBlack300

)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLightGreen600,
    secondary = SecondaryDirtyGreen500,
    tertiary = Color.White,
    background = Color.Black,
    onBackground = Color.Black

)

@Composable
fun SwipeifyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    println(darkTheme)
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }


    MaterialTheme(
        //TODO: CURRENTLY HARD CODED TO DARK THEME instead of colorScheme = colorScheme
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}