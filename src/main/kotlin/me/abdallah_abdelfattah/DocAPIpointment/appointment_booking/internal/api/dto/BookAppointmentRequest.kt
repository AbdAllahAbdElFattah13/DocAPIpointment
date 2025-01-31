package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto

data class BookAppointmentRequest(
    val patientId: String,
    val slotId: String,
)
