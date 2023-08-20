package pl.inpost.recruitmenttask.features.shipmentList.domain.mapper

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

private const val SHIPMENT_DATE_PATTERN = "dd.MM.yy | HH:mm"
internal class ShipmentItemDateFormatter @Inject constructor() {

    private val formatter = DateTimeFormatter.ofPattern(SHIPMENT_DATE_PATTERN)

    fun format(date: ZonedDateTime): String {
        val dayOfWeekShort = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())

        return "${dayOfWeekShort}. | ${date.format(formatter)}"
    }
}