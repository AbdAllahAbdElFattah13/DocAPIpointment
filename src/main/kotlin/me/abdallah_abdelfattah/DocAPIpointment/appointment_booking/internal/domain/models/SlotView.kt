package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models

data class SlotView(
    val id: String,
    val doctorId: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val reserved: Boolean,
    val cost: String,
)
