package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import java.time.Clock

data class Appointment(
    val id: GUID = GUID(),
    val doctorId: GUID,
    val slotId: GUID,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val patient: Patient,
    val status: AppointmentStatus = AppointmentStatus.OPENED,
    val lastUpdatedAtEpoch: Long? = null,
    val reservedAtEpoch: Long? = null,
    val createdAtEpoch: Long = Clock.systemUTC().instant().toEpochMilli(),
) {
    private fun updateStatus(status: AppointmentStatus) = copy(
        status = status,
        lastUpdatedAtEpoch = Clock.systemUTC().instant().toEpochMilli(),
    )

    fun cancel() = updateStatus(status = AppointmentStatus.CANCELLED)
    fun complete() = updateStatus(status = AppointmentStatus.COMPLETED)
    fun reopen() = updateStatus(status = AppointmentStatus.OPENED)
}
