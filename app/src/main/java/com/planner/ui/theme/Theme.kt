package com.planner.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.planner.ui.theme.shapes

private val DarkColorPalette = darkColors(
    /*primary = primaryDarkColor,
    primaryVariant = primaryColor,
    secondary = secondaryDarkColor,
    onPrimary = primaryTextColor,
    onSecondary = secondaryTextColor,*/

    primary = purple200,
    primaryVariant = purple700,
    secondary = teal200


)

private val LightColorPalette = lightColors(
    primary = purple500,
    primaryVariant = purple700,
    secondary = teal200

    /*primary = primaryLightColor,
    primaryVariant = primaryColor,
    secondary = secondaryLightColor,
    secondaryVariant = secondaryColor,
    onPrimary = Color.White,
    onSecondary = secondaryTextColor*/

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PlannerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}