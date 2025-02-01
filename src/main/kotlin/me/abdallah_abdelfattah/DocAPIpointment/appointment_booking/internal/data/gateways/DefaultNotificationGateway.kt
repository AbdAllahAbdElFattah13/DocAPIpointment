package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.NotificationGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.api.NotificationAPI
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests.AppointmentConfirmationNotificationRequest
import org.springframework.stereotype.Component

@Component
class DefaultNotificationGateway(
    private val notificationAPI: NotificationAPI
) : NotificationGateway {
    override fun notifyNewAppointment(
        slotId: String,
        patientId: String,
        patientName: String,
        doctorId: String,
        appointmentTimeEpoch: Long,
    ) {
        notificationAPI.sendAppointmentNotification(
            AppointmentConfirmationNotificationRequest(
                slotId = slotId,
                patientId = patientId,
                patientName = patientName,
                doctorId = doctorId,
                appointmentTimeEpoch = appointmentTimeEpoch,
            )
        )
    }
}
