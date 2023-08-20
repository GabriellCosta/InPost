package pl.inpost.recruitmenttask.presentation.shipmentList.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.inpost.recruitmenttask.R
import pl.inpost.recruitmenttask.theme.InPostTheme

data class ShipmentItemUIModel(
    val number: String,
    val icon: Int,
    @StringRes val status: Int,
    val detail: ShipmentItemDetailLabelUIModel?,
    val contact: String,
)

data class ShipmentItemDetailLabelUIModel(
    val label: String,
    val value: String,
)

@Composable
fun ShipmentItemComposable(
    modifier: Modifier = Modifier,
    model: ShipmentItemUIModel,
) {
    Surface(
        modifier = modifier
            .background(MaterialTheme.colors.background)
            .padding(
                horizontal = 20.dp,
            )
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Column {
            TopPart(model)
            MiddlePart(Modifier.padding(top = 16.dp), model)
            BottomPart(Modifier.padding(top = 16.dp), model)
        }
    }
}

@Composable
private fun TopPart(model: ShipmentItemUIModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                stringResource(id = R.string.shipment_screen_item_number),
                style = InPostTheme.typography.label,
                color = Color(0xFF929497)
            )
            Text(
                text = model.number,
                style = InPostTheme.typography.body,
            )
        }

        Image(
            painter = painterResource(model.icon),
            contentDescription = "",
        )
    }
}

@Composable
private fun MiddlePart(modifier: Modifier = Modifier, model: ShipmentItemUIModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f, true),
        ) {
            Text(
                "STATUS",
                style = InPostTheme.typography.label,
                color = Color(0xFF929497)
            )
            Text(
                text = stringResource(id = model.status),
                style = InPostTheme.typography.bodyLarge,
            )
        }

        model.detail?.let { detail ->
            Column {
                Text(
                    detail.label,
                    style = InPostTheme.typography.label,
                    color = Color(0xFF929497)
                )
                Text(
                    text = detail.value,
                    style = InPostTheme.typography.body,
                )
            }
        }

        /*Image(
            painter = painterResource(R.drawable.ic_kurier),
            contentDescription = "",
        )*/
    }
}

@Composable

private fun BottomPart(modifier: Modifier = Modifier, model: ShipmentItemUIModel) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.shipment_screen_item_contact),
                style = InPostTheme.typography.label,
                color = Color(0xFF929497)
            )
            Text(
                text = model.contact,
                style = InPostTheme.typography.bodyLarge,
            )
        }

        TextButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.shipment_screen_item_action_button),
                style = InPostTheme.typography.button,
                color = Color(0xFF404041),
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "",
            )
        }
    }
}

@Composable
@Preview(widthDp = 360)
fun PreviewDeliveredComposable() {
    InPostTheme {
        val model = ShipmentItemUIModel(
            number = "235678654323567889762231",
            icon = R.drawable.ic_kurier,
            status = R.string.status_out_for_delivery,
            contact = "adresmailowy@mail.pl",
            detail = null,
        )

        ShipmentItemComposable(model = model)
    }
}

@Composable
@Preview(widthDp = 360)
fun PreviewReadyToPickup() {
    InPostTheme {
        val model = ShipmentItemUIModel(
            number = "235678654323567889762231",
            icon = R.drawable.ic_packing,
            status = R.string.status_ready_to_pickup,
            contact = "adresmailowy@mail.pl",
            detail = ShipmentItemDetailLabelUIModel(
                label = "CZEKA NA ODBIÓR DO",
                value = "pn. | 14.06.18 | 11:30",
            )
        )

        ShipmentItemComposable(model = model)
    }
}

@Composable
@Preview(widthDp = 360)
fun PreviewReceived() {
    InPostTheme {
        val model = ShipmentItemUIModel(
            number = "235678654323567889762231",
            icon = R.drawable.ic_packing,
            status = R.string.status_delivered,
            contact = "adresmailowy@mail.pl",
            detail = ShipmentItemDetailLabelUIModel(
                label = "ODEBRANA",
                value = "pn. | 14.06.18 | 11:30",
            )
        )

        ShipmentItemComposable(model = model)
    }
}