package com.yang.study_compose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.yang.study_compose.R

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

@Composable
fun JetPackTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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

@Composable
fun CustomerColorTheme(
    isDark: Boolean,
    content: @Composable () -> Unit
) {
    var BLUE = Color(0xFF0000FF)
    var RED = Color(0xFFDC143C)
    val colors = if (isDark) {
        darkColors(
            primary = BLUE
        )
    } else {
        lightColors(
            primary = RED
        )
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

open class CustomerAssets private constructor(
    var background: Int,
    var illos: Int,
    var logo: Int
) {

    object LightCustomerAssets : CustomerAssets(
        background = R.drawable.ic_launcher_background,
        illos = R.drawable.ic_capital_hide_dark,
        logo = R.drawable.ic_launcher_foreground
    )

    object DarkCustomerAssets : CustomerAssets(
        background = R.drawable.ic_launcher_background,
        illos = R.drawable.ic_capital_visible_dark,
        logo = R.drawable.ic_launcher_foreground
    )
}

internal var LocalCustomerAssets = staticCompositionLocalOf {
    CustomerAssets.LightCustomerAssets as CustomerAssets
}

val MaterialTheme.customerAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomerAssets.current