package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers.AppointmentMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.GetUpcomingAppointmentsByDoctorIdUseCase
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.UpdateAppointmentStatusUseCase
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO
import org.springframework.stereotype.Component

@Component
class DefaultAppointmentBookingAPI(
    private val getUpcomingAppointmentsByDoctorIdUseCase: GetUpcomingAppointmentsByDoctorIdUseCase,
    private val updateAppointmentStatusUseCase: UpdateAppointmentStatusUseCase,
    private val mapper: AppointmentMapper,
) : AppointmentBookingAPI {
    override fun getUpcomingAppointments(doctorId: String, nowEpochMilli: Long): List<AppointmentDTO> =
        getUpcomingAppointmentsByDoctorIdUseCase.run(doctorId, nowEpochMilli).map(mapper::toAppointmentDTO)


    override fun markAppointmentAsComplete(appointmentId: String) =
        updateAppointmentStatusUseCase.run(appointmentId, AppointmentStatus.COMPLETED)

    override fun cancelAppointment(appointmentId: String) =
        updateAppointmentStatusUseCase.run(appointmentId, AppointmentStatus.CANCELLED)

    override fun reopenAppointment(appointmentId: String) =
        updateAppointmentStatusUseCase.run(appointmentId, AppointmentStatus.REOPENED)
}
