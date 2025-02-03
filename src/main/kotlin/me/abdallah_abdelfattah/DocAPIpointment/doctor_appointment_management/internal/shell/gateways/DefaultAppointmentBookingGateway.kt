package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.api.AppointmentBookingAPI
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import org.springframework.stereotype.Component

@Component
class DefaultAppointmentBookingGateway(
    private val api: AppointmentBookingAPI,
    private val mapper: AppointmentGatewayMapper,
) : AppointmentBookingGateway {
    override fun getUpcomingAppointments(doctorId: String, nowEpochMillis: Long): List<AppointmentView> =
        api.getUpcomingAppointments(doctorId, nowEpochMillis).map(mapper::toAppointmentView)

    override fun markAppointmentAsComplete(appointmentId: String) =
        api.markAppointmentAsComplete(appointmentId)

    override fun cancelAppointment(appointmentId: String) =
        api.cancelAppointment(appointmentId)

    override fun reopenAppointment(appointmentId: String) {
        api.reopenAppointment(appointmentId)
    }
}
