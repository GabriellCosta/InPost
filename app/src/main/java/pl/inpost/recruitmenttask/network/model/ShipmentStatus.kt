package pl.inpost.recruitmenttask.network.model

import androidx.annotation.StringRes
import pl.inpost.recruitmenttask.R

/**
 * Order of statuses
 * 1. CREATED
 * 2. CONFIRMED
 * 3. ADOPTED_AT_SOURCE_BRANCH
 * 4. SENT_FROM_SOURCE_BRANCH
 * 5. ADOPTED_AT_SORTING_CENTER
 * 6. SENT_FROM_SORTING_CENTER
 * 7. OTHER
 * 8. DELIVERED
 * 9. RETURNED_TO_SENDER
 * 10. AVIZO
 * 11. OUT_FOR_DELIVERY
 * 12. READY_TO_PICKUP
 * 13. PICKUP_TIME_EXPIRED
 */
enum class ShipmentStatus(
    @StringRes val nameRes: Int,
    val priority: Int,
) {
    CREATED(R.string.status_created, 1),
    CONFIRMED(R.string.status_confirmed, 2),
    ADOPTED_AT_SOURCE_BRANCH(R.string.status_adopted_at_source_branch, 3),
    SENT_FROM_SOURCE_BRANCH(R.string.status_sent_from_source_branch, 4),
    ADOPTED_AT_SORTING_CENTER(R.string.status_adopted_at_sorting_center, 5),
    SENT_FROM_SORTING_CENTER(R.string.status_sent_from_sorting_center, 6),
    OTHER(R.string.status_other, 7),
    DELIVERED(R.string.status_delivered, 8),
    RETURNED_TO_SENDER(R.string.status_returned_to_sender, 9),
    AVIZO(R.string.status_avizo, 10),
    OUT_FOR_DELIVERY(R.string.status_out_for_delivery, 11),
    READY_TO_PICKUP(R.string.status_ready_to_pickup, 12),
    PICKUP_TIME_EXPIRED(R.string.status_pickup_time_expired, 13),
}
