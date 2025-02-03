package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models

data class AppointmentView(
    val id: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val patientId: String,
    val patientName: String,
    val status: String,
)
