package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender

import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.Logger
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LogNotificationSenderTest {

    private val logger: Logger = mock()

    @Test
    fun `send logs the notification`() {
        val notification = AppointmentConfirmationNotification(
            patientName = "patientName",
            doctorName = "doctorName",
            appointmentTimeEpoch = 1234567890,
        )

        val logNotificationSender = LogNotificationSender(logger)
        logNotificationSender.send(notification)

        verify(logger).info(
            """
            New Appointment Booked!
            Patient: ${notification.patientName}
            Doctor: ${notification.doctorName}
            Time: 15/1/1970 6:56 AM
        """.trimIndent()
        )
    }

}
