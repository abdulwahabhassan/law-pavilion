package com.example.lawpavilion.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BackgroundNavyBlue,
    primaryVariant = BackgroundNavyBlueVariant,
    onPrimary = NavTextLightPurple,
    secondary = Orange,
    onSecondary = TextWhite,
    onBackground = BodyTextBlack,
    error = ButtonRed,
    onError = TextWhite
)

private val LightColorPalette = lightColors(
    primary = BackgroundNavyBlue,
    primaryVariant = BackgroundNavyBlueVariant,
    onPrimary = NavTextLightPurple,
    secondary = Orange,
    onSecondary = TextWhite,
    onBackground = BodyTextBlack,
    error = ButtonRed,
    onError = TextWhite
)

@Composable
fun LawPavilionTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}