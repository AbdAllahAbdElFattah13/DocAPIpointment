package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender.AppointmentConfirmationNotification
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender.NotificationSender
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests.AppointmentConfirmationNotificationRequest
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DefaultDoctorAvailabilityAPI
import org.springframework.stereotype.Component

@Component
class DefaultNotificationAPI(
    private val notificationSender: NotificationSender,
    private val doctorAvailabilityAPI: DefaultDoctorAvailabilityAPI,
) : NotificationAPI {

    override fun sendAppointmentNotification(request: AppointmentConfirmationNotificationRequest) {

        val doctor = doctorAvailabilityAPI.getDoctorInfo(request.doctorId)

        require(doctor != null) { "Unknown doctor with id ${request.doctorId}" }

        val notification = AppointmentConfirmationNotification(
            patientName = request.patientName,
            doctorName = doctor.name,
            appointmentTimeEpoch = request.appointmentTimeEpoch,
        )

        notificationSender.send(notification)
    }

}
