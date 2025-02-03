package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.dto

data class UpdateAppointmentStatusRequest(
    val appointmentId: String,
    val appointmentStatus: String,
)
