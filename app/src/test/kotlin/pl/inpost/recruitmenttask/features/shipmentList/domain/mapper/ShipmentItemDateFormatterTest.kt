package pl.inpost.recruitmenttask.features.shipmentList.domain.mapper

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime
import java.time.format.TextStyle
import java.util.Locale

internal class ShipmentItemDateFormatterTest {

    private val subject = ShipmentItemDateFormatter()

    @Before
    fun setUp() {
        Locale.setDefault(Locale.US)
    }

    @After
    fun tearDown() {
        Locale.setDefault(Locale.US)
    }

    @Test
    fun format_validZonedDateTime_correctFormattedString() {
        val mockDate = ZonedDateTime.parse("2023-08-20T12:34:56+00:00[UTC]")

        val result = subject.format(mockDate)

        val expectedDay = mockDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val expectedFormattedDate = "20.08.23 | 12:34"
        assertEquals("$expectedDay. | $expectedFormattedDate", result)
    }

    @Test
    fun format_differentTimeZone_correctFormattedString() {
        val parameter = ZonedDateTime.parse("2023-08-20T12:34:56+03:00[Europe/Moscow]")
        val expectedDay = parameter.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val expectedFormattedDate = "20.08.23 | 12:34"
        val expected = "$expectedDay. | $expectedFormattedDate"

        val result = subject.format(parameter)


        assertEquals(expected, result)
    }

    @Test
    fun format_differentLocale_correctFormattedString() {
        // Mocking static methods with mockk
        Locale.setDefault(Locale("fr", "FR"))
        val parameter = ZonedDateTime.parse("2023-08-20T12:34:56+00:00[UTC]")
        val expectedDay = parameter.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
        val expectedFormattedDate = "20.08.23 | 12:34"
        val expected = "$expectedDay. | $expectedFormattedDate"

        val result = subject.format(parameter)

        assertEquals(expected, result)
    }
}
