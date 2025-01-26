package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.models.GUID

data class Slot(
    val id: GUID = GUID(),
    val doctorId: GUID,
    val time: FutureDate,
    val durationInMinutes: Int = 2*60,
    val isReserved: Boolean,
    val cost: Cost,
) {
    private val minDuration = 15

    init {
        require(durationInMinutes >= minDuration) { "Duration must be greater than $minDuration" }
    }

    fun reserve() = copy(isReserved = true)
}
