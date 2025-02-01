package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender

import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.Logger
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class LogNotificationSender(
    private val logger: Logger
) : NotificationSender {

    override fun send(notification: AppointmentConfirmationNotification) {
        val formattedTime = DateTimeFormatter
            .ofPattern("d/M/yyyy h:mm a")
            .withZone(ZoneId.of("UTC"))
            .format(Instant.ofEpochMilli(notification.appointmentTimeEpoch))

        logger.info(
            """
            New Appointment Booked!
            Patient: ${notification.patientName}
            Doctor: ${notification.doctorName}
            Time: $formattedTime
        """.trimIndent()
        )
    }
}
