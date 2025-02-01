package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender

interface NotificationSender {
    fun send(notification: AppointmentConfirmationNotification)
}
