package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto


data class ResponseSlot(
    val id: String,
    val time: String,
    val durationInMinutes: Int,
    val reserved: Boolean,
    val cost: String
)

data class GetDoctorAvailabilityServiceResponse(
    val doctorId: String,
    val slots: List<ResponseSlot>
)
