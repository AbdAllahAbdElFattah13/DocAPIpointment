package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests.AppointmentConfirmationNotificationRequest

interface NotificationAPI {
    fun sendAppointmentNotification(request: AppointmentConfirmationNotificationRequest)
}
