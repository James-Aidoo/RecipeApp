package com.questdev.recipeapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Green600,
    primaryVariant = Green800,
    secondary = Magenta500,
    secondaryVariant = Magenta700,
    background = Black900,
    surface = Black800,
    onBackground = White50,
    onPrimary = White50,
    onSecondary = White50,
    onSurface = White50
)

private val LightColorPalette = lightColors(
    primary = Green600,
    primaryVariant = Green800,
    secondary = Magenta500,
    secondaryVariant = Magenta700,
    background = White100,
    surface = White200,
    onPrimary = White50,
    onSecondary = White50,
    onBackground = Black900,
    onSurface = Black900,
)

@Composable
fun RecipeAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}