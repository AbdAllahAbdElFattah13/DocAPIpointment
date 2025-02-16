package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway

interface AppointmentStatusHandler {
    fun handleAppointmentStatus(appointmentId: String)
}

class CompletedAppointmentStatusHandler(
    private val gateway: AppointmentBookingGateway
) : AppointmentStatusHandler {
    override fun handleAppointmentStatus(appointmentId: String) =
        gateway.markAppointmentAsComplete(appointmentId)
}

class CancelledAppointmentStatusHandler(
    private val gateway: AppointmentBookingGateway
) : AppointmentStatusHandler {
    override fun handleAppointmentStatus(appointmentId: String) =
        gateway.cancelAppointment(appointmentId)
}

class ReopenedAppointmentStatusHandler(
    private val gateway: AppointmentBookingGateway
) : AppointmentStatusHandler {
    override fun handleAppointmentStatus(appointmentId: String) =
        gateway.reopenAppointment(appointmentId)
}
