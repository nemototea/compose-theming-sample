package com.example.sampleapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.sampleapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

enum class ColorPalette(
    val basic: Color,
    val highlight: Color,
    val dark: Color
) {
    LOID(
        LoidColors.basic,
        LoidColors.highlight,
        LoidColors.dark
    ),
    YOR(
        YorColors.basic,
        YorColors.highlight,
        YorColors.dark
    ),
    ANYA(
        AnyaColors.basic,
        AnyaColors.highlight,
        AnyaColors.dark
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

        @Composable
        fun ColorPalette.toPainter(): Painter {
            return if (isSystemInDarkTheme()) {
                when (this) {
                    LOID -> painterResource(id = R.drawable.loiddark)
                    YOR -> painterResource(id = R.drawable.yordark)
                    ANYA -> painterResource(id = R.drawable.anyadark)
                }
            } else {
                when (this) {
                    LOID -> painterResource(id = R.drawable.loidlight)
                    YOR -> painterResource(id = R.drawable.yorlight)
                    ANYA -> painterResource(id = R.drawable.anyalight)
                }
            }
        }
    }
}

internal val LocalColorPalette = staticCompositionLocalOf { ColorPalette.LOID }

object SampleAppTheme {
    val current: ColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalColorPalette.current
}

@Composable
fun SampleAppTheme(
    colorPalette: ColorPalette,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val sysUiController = rememberSystemUiController()
    LaunchedEffect(colorPalette, darkTheme) {
        val color = if (darkTheme) {
            colorPalette.dark
        } else {
            colorPalette.highlight
        }
        sysUiController.setSystemBarsColor(color)
    }

    CompositionLocalProvider(LocalColorPalette provides colorPalette) {
        MaterialTheme(
            colors = lightColors(),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}
