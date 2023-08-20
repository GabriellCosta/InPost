package pl.inpost.recruitmenttask.presentation.shipmentList.domain.mapper

import pl.inpost.recruitmenttask.R
import javax.inject.Inject
import kotlin.jvm.Throws

class StatusToResourceMapper @Inject constructor() {

    @Throws(IllegalArgumentException::class)
    fun mapFrom(from: String): Int {
        return when(from) {
            "READY_TO_PICKUP" -> R.string.status_ready_to_pickup
            "CONFIRMED" -> R.string.status_confirmed
            "ADOPTED_AT_SORTING_CENTER" -> R.string.status_adopted_at_sorting_center
            "SENT_FROM_SORTING_CENTER" -> R.string.status_sent_from_sorting_center
            "ADOPTED_AT_SOURCE_BRANCH" -> R.string.status_adopted_at_source_branch
            "SENT_FROM_SOURCE_BRANCH" -> R.string.status_sent_from_source_branch
            "AVIZO" -> R.string.status_avizo
            "CREATED" -> R.string.status_created
            "DELIVERED" -> R.string.status_delivered
            "OTHER" -> R.string.status_other
            "OUT_FOR_DELIVERY" -> R.string.status_out_for_delivery
            "PICKUP_TIME_EXPIRED" -> R.string.status_pickup_time_expired
            "RETURNED_TO_SENDER" -> R.string.status_returned_to_sender
            else -> throw IllegalArgumentException("Unknown status: $from")
        }
    }

}