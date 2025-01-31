package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos

data class SlotDTO(
    val id: String,
    val doctorId: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val reserved: Boolean,
    val cost: String,
)
