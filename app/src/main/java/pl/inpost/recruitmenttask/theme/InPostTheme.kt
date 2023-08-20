package pl.inpost.recruitmenttask.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

@Composable
fun InPostTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(content = content)
}

object InPostTheme {
    val typography: Typography
        @Composable
        get() = inPostTypography
}