package pl.inpost.recruitmenttask.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun InPostTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(content = content)
}