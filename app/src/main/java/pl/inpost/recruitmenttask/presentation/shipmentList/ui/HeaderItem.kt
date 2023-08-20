package pl.inpost.recruitmenttask.presentation.shipmentList.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.theme.InPostTheme

@Composable
fun ItemSeparator(
    text: String
) {

    Row(
        modifier = Modifier
            .background(Color(0xFFF2F2F2))
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ItemDivider()

        Text(
            text = text,
            color = Color(0xFFBBBDBF),
            style = InPostTheme.typography.header,
        )

        ItemDivider()
    }
}

@Composable
private fun RowScope.ItemDivider() {
    Divider(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .weight(1f, fill = false),
        color = Color(0xFFE9E9E9),
        thickness = 1.dp
    )
}


@Composable
@Preview
private fun PreviewItemSeparator() {
    InPostTheme() {
        Row {
            ItemSeparator("Gotowe do odbioru")
        }
    }
}