package pl.inpost.recruitmenttask.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private const val OPAQUE_20 = 0.2F
private const val HALF_LUMINANCE = 0.5F

@Composable
fun InPostSystemUi(
    statusBarColor: Color,
    navigationBarColor: Color,
) {
    val darkIconsOnStatusBar = useDarkIconsForColor(color = statusBarColor)
    val darkIconsOnNavigationBar = useDarkIconsForColor(color = navigationBarColor)

    with(rememberSystemUiController()) {
        SideEffect {
            setStatusBarColor(
                color = statusBarColor,
                darkIcons = darkIconsOnStatusBar,
                transformColorForLightContent = BlackScrimmed,
            )
            setNavigationBarColor(
                color = navigationBarColor,
                darkIcons = darkIconsOnNavigationBar,
                transformColorForLightContent = BlackScrimmed,
            )
        }
    }
}

@Composable
private fun useDarkIconsForColor(color: Color) =
    if (color == Transparent) MaterialTheme.colors.isLight else color.luminance() > HALF_LUMINANCE

private val BlackScrim = Color(0f, 0f, 0f, OPAQUE_20) // 20% opaque black
private val BlackScrimmed: (Color) -> Color = { original ->
    BlackScrim.compositeOver(original)
}
