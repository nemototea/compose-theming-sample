package com.example.sampleapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

enum class ColorPalette(
    val basic: Color,
    val background: Color,
    val highlight: Color,
    val dark: Color,
    val materialColors: Colors = lightColors()
) {
    LOID(

    ),
    YOR(

    ),
    ANYA(

    );

    companion object {

        @Composable
        fun ColorPalette.toName(): String {
            return when (this) {
                LOID -> "Loid Forger(Twilight)"
                YOR -> "Yor Forger(Briar Rose)"
                ANYA -> "Anya Forger(Subject 007)"
            }
        }
    }
}


@Composable
fun SampleAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val sysUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        sysUiController.setStatusBarColor(
            color = Color.Gray
        )
    }

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
