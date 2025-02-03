package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers.AppointmentMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.GetUpcomingAppointmentsByDoctorIdUseCase
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.UpdateAppointmentStatusUseCase
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class DefaultAppointmentBookingAPITest {

    @Test
    fun `getUpcomingAppointments should return upcoming appointments`() {
        val getUpcomingAppointmentsByDoctorIdUseCase: GetUpcomingAppointmentsByDoctorIdUseCase = mock()
        val appointment: Appointment = mock()
        val appointmentDTO: AppointmentDTO = mock()
        val mapper: AppointmentMapper = mock {
            on(it.toAppointmentDTO(appointment)).thenReturn(appointmentDTO)
        }
        val epochMilli = 1234567890L
        val doctorId = "doctorId"

        val defaultAppointmentBookingAPI = DefaultAppointmentBookingAPI(
            getUpcomingAppointmentsByDoctorIdUseCase,
            mock(),
            mapper,
        )

        defaultAppointmentBookingAPI.getUpcomingAppointments(doctorId, epochMilli)

        verify(getUpcomingAppointmentsByDoctorIdUseCase, times(1)).run(doctorId, epochMilli)
    }

    @Test
    fun `markAppointmentAsComplete should call updateAppointmentStatusUseCase with COMPLETED status`() {
        val updateAppointmentStatusUseCase: UpdateAppointmentStatusUseCase = mock()
        val appointmentId = "appointmentId"
        val defaultAppointmentBookingAPI = DefaultAppointmentBookingAPI(
            mock(),
            updateAppointmentStatusUseCase,
            mock(),
        )

        defaultAppointmentBookingAPI.markAppointmentAsComplete(appointmentId)

        verify(updateAppointmentStatusUseCase, times(1)).run(appointmentId, AppointmentStatus.COMPLETED)
    }

    @Test
    fun `cancelAppointment should call updateAppointmentStatusUseCase with CANCELLED status`() {
        val updateAppointmentStatusUseCase: UpdateAppointmentStatusUseCase = mock()
        val appointmentId = "appointmentId"
        val defaultAppointmentBookingAPI = DefaultAppointmentBookingAPI(
            mock(),
            updateAppointmentStatusUseCase,
            mock(),
        )

        defaultAppointmentBookingAPI.cancelAppointment(appointmentId)

        verify(updateAppointmentStatusUseCase, times(1)).run(appointmentId, AppointmentStatus.CANCELLED)
    }

    @Test
    fun `reopenAppointment should call updateAppointmentStatusUseCase with REOPEN status`() {
        val updateAppointmentStatusUseCase: UpdateAppointmentStatusUseCase = mock()
        val appointmentId = "appointmentId"
        val defaultAppointmentBookingAPI = DefaultAppointmentBookingAPI(
            mock(),
            updateAppointmentStatusUseCase,
            mock(),
        )

        defaultAppointmentBookingAPI.reopenAppointment(appointmentId)

        verify(updateAppointmentStatusUseCase, times(1)).run(appointmentId, AppointmentStatus.REOPENED)
    }
}
