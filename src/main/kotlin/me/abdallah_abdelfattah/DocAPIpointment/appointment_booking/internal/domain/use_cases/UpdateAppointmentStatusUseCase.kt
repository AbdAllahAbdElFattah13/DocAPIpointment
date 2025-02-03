package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import org.springframework.stereotype.Service

@Service
class UpdateAppointmentStatusUseCase(
    private val appointmentRepository: AppointmentRepository,
) {
    fun run(appointmentId: String, newStatus: AppointmentStatus) {
        val appointment = appointmentRepository.findById(appointmentId)
            ?: throw IllegalArgumentException("Appointment with id $appointmentId not found")

        require(newStatus != AppointmentStatus.OPENED) {
            "Appointment with id $appointmentId can only be OPEN once, use REOPEN instead"
        }

        appointmentRepository.update(
            appointmentId,
            appointment.copy(status = newStatus),
        )
    }
}
