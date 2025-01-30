package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID

data class Slot(
    val id: GUID = GUID(),
    val doctorId: GUID,
    val time: FutureDate,
    val cost: Cost,
    val durationInMinutes: Int = 2*60,
    val isReserved: Boolean = false,
) {
    private val minDuration = 15
    val endEpochMillis: Long =  time.epochMillis + durationInMinutes * 60 * 1000

    init {
        require(durationInMinutes >= minDuration) { "Slot: Duration must be greater than $minDuration" }
    }

    fun reserve() = copy(isReserved = true)
}
