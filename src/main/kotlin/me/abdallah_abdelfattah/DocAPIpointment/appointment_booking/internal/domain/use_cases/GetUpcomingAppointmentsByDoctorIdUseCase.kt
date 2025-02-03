package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import org.springframework.stereotype.Service

@Service
class GetUpcomingAppointmentsByDoctorIdUseCase(
    private val appointmentRepository: AppointmentRepository,
) {
    fun run(
        doctorId: String,
        nowEpochMilli: Long,
    ): List<Appointment> = appointmentRepository.getUpcomingAppointmentsByDoctorId(
        doctorId = doctorId,
        nowEpochMilli = nowEpochMilli,
    )
}
