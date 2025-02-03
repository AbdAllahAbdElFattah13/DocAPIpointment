package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import org.assertj.core.api.Assertions.assertThat
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test

class GetUpcomingAppointmentsByDoctorIdUseCaseTest {

    @Test
    fun `run should return upcoming appointments for the given doctor`() {

        val appointmentRepository: AppointmentRepository = mock()
        val useCase = GetUpcomingAppointmentsByDoctorIdUseCase(appointmentRepository)

        val appointment: Appointment = mock()
        val appointments = listOf(appointment)
        val doctorId = "doctorId"
        val nowEpochMilli = 123L

        whenever(
            appointmentRepository.getUpcomingAppointmentsByDoctorId(
                doctorId,
                nowEpochMilli
            )
        ) doReturn (appointments)

        val result = useCase.run(doctorId, nowEpochMilli)

        assertThat(result).containsExactly(appointment)
    }
}
