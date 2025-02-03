package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO


interface AppointmentBookingAPI {
    fun getUpcomingAppointments(doctorId: String, nowEpochMilli: Long): List<AppointmentDTO>
    fun markAppointmentAsComplete(appointmentId: String)
    fun cancelAppointment(appointmentId: String)
    fun reopenAppointment(appointmentId: String)
}
