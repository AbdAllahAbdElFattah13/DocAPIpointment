package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.api.NotificationAPI
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests.AppointmentConfirmationNotificationRequest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class DefaultNotificationGatewayTest {

    private val notificationAPI: NotificationAPI = mock()

    private val defaultNotificationGateway = DefaultNotificationGateway(notificationAPI)

    @Test
    fun `notifyNewAppointment should call notificationAPI sendAppointmentNotification`() {
        val slotId = "slotId"
        val patientId = "patientId"
        val patientName = "patientName"
        val doctorId = "doctorId"
        val appointmentTimeEpoch = 123L

        defaultNotificationGateway.notifyNewAppointment(
            slotId = slotId,
            patientId = patientId,
            patientName = patientName,
            doctorId = doctorId,
            appointmentTimeEpoch = appointmentTimeEpoch,
        )

        verify(notificationAPI, times(1))
            .sendAppointmentNotification(
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
