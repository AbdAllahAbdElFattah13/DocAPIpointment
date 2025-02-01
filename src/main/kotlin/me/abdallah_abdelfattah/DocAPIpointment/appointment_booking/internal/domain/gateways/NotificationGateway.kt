package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways


interface NotificationGateway {
    fun notifyNewAppointment(
        slotId: String,
        patientId: String,
        patientName: String,
        doctorId: String,
        appointmentTimeEpoch: Long,
    )
}
