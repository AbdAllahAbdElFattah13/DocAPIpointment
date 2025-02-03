package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto

data class AppointmentDTO(
    val id: String,
    val doctorId: String,
    val patientId: String,
    val patientName: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val status: String,
)
