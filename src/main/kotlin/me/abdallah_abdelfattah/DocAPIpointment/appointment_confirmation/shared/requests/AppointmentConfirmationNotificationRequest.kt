package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests

data class AppointmentConfirmationNotificationRequest(
    val slotId: String,
    val patientId: String,
    val patientName: String,
    val doctorId: String,
    val appointmentTimeEpoch: Long
)
