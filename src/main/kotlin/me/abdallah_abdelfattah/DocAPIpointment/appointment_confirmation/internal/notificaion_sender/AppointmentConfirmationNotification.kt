package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender

data class AppointmentConfirmationNotification(
    val patientName: String,
    val doctorName: String,
    val appointmentTimeEpoch: Long,
)
