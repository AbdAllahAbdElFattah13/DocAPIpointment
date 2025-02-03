package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class AppointmentOperationsAdapter(
    private val appointmentBookingGateway: AppointmentBookingGateway,
) : AppointmentOperationsPort {
    //TODO: this will require a bit of refactoring, as the getUpcomingAppointments is creating the current time in the adapter, which is not a good practice, nor testable.
    override fun getUpcomingAppointments(doctorId: String): List<AppointmentView> =
        appointmentBookingGateway.getUpcomingAppointments(
            doctorId,
            Clock.systemUTC().instant().toEpochMilli(),
        )

    override fun updateAppointmentStatus(
        appointmentId: String,
        status: AppointmentStatusView,
    ) {
        when (status) {
            AppointmentStatusView.COMPLETED -> {
                appointmentBookingGateway.markAppointmentAsComplete(appointmentId)
            }

            AppointmentStatusView.CANCELLED -> {
                appointmentBookingGateway.cancelAppointment(appointmentId)
            }

            AppointmentStatusView.REOPENED -> {
                appointmentBookingGateway.reopenAppointment(appointmentId)
            }

            AppointmentStatusView.OPENED -> {
                throw UnsupportedOperationException(
                    "Cannot set appointment status to OPEN, use REOPEN instead if you want to reopen a closed appointment"
                )
            }
        }
    }
}
