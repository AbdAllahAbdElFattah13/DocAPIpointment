package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView

interface AppointmentOperationsPort {
    fun getUpcomingAppointments(doctorId: String): List<AppointmentView>
    fun updateAppointmentStatus(appointmentId: String, status: AppointmentStatusView)
}
