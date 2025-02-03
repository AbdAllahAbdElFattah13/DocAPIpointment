package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto


data class AppointmentResponse(
    val id: String,
    val startTimeEpoch: Long,
    val endTimeEpoch: Long,
    val patientId: String,
    val patientName: String,
    val status: String,
)

data class ViewAllDoctorUpcomingAppointmentsResponse(
    val appointments: List<AppointmentResponse>
)
