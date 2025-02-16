package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.AppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class AppointmentOperationsAdapter(
    private val appointmentBookingGateway: AppointmentBookingGateway,
    private val appointmentStatusHandlers: Map<AppointmentStatusView, AppointmentStatusHandler>,
    private val clock: Clock,
) : AppointmentOperationsPort {
    override fun getUpcomingAppointments(doctorId: String): List<AppointmentView> =
        appointmentBookingGateway.getUpcomingAppointments(
            doctorId,
            clock.instant().toEpochMilli(),
        )

    override fun updateAppointmentStatus(
        appointmentId: String,
        status: AppointmentStatusView,
    ) = appointmentStatusHandlers[status]?.handleAppointmentStatus(appointmentId)
        ?: throw IllegalArgumentException("Invalid status: $status")
}
