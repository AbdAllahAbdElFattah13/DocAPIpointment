package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView

interface AppointmentBookingGateway {
    fun getUpcomingAppointments(doctorId: String, nowEpochMillis: Long): List<AppointmentView>
    fun markAppointmentAsComplete(appointmentId: String)
    fun cancelAppointment(appointmentId: String)
    fun reopenAppointment(appointmentId: String)
}
