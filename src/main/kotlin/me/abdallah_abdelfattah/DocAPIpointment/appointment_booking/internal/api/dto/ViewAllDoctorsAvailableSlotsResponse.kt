package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto


data class ResponseSlot(
    val id: String,
    val doctorId: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val reserved: Boolean,
    val cost: String,
)

data class ViewAllDoctorsAvailableSlotsResponse(
    val slots: List<ResponseSlot>
)
