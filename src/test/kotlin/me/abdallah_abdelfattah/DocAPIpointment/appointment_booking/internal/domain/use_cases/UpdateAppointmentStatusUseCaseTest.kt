package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UpdateAppointmentStatusUseCaseTest {

    private val patient = Patient(
        id = GUID(value = "6c26cc8b-6f02-4e06-8c02-a070388934c5"),
        name = Name("Mohamed El-Shazly"),
    )
    private val doctorId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c4")
    private val slotId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c3")
    private val appointmentId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c2")
    private val startTimeEpoch = 1321300000000L
    private val endTimeEpoch = 14200930000000L

    @Test
    fun `run should update the appointment status to the new status`() {
        val appointment = Appointment(
            id = appointmentId,
            patient = patient,
            doctorId = doctorId,
            slotId = slotId,
            startTimeEpoch = startTimeEpoch,
            endTimeEpoch = endTimeEpoch,
            status = AppointmentStatus.OPENED,
        )
        val newStatus = AppointmentStatus.CANCELLED
        val appointmentRepository = mock<AppointmentRepository>()
        whenever(appointmentRepository.findById(appointment.id.value)).thenReturn(appointment)
        val updateAppointmentStatusUseCase = UpdateAppointmentStatusUseCase(appointmentRepository)

        updateAppointmentStatusUseCase.run(appointmentId.value, newStatus)

        verify(appointmentRepository, times(1)).update(appointmentId.value, appointment.copy(status = newStatus))
    }

    @Test
    fun `run should be able to reopen appointments normally`() {
        val appointment = Appointment(
            id = appointmentId,
            patient = patient,
            doctorId = doctorId,
            slotId = slotId,
            startTimeEpoch = startTimeEpoch,
            endTimeEpoch = endTimeEpoch,
            status = AppointmentStatus.CANCELLED,
        )
        val newStatus = AppointmentStatus.REOPENED
        val appointmentRepository = mock<AppointmentRepository>()
        whenever(appointmentRepository.findById(appointment.id.value)).thenReturn(appointment)
        val updateAppointmentStatusUseCase = UpdateAppointmentStatusUseCase(appointmentRepository)

        updateAppointmentStatusUseCase.run(appointmentId.value, newStatus)

        verify(appointmentRepository, times(1)).update(appointmentId.value, appointment.copy(status = newStatus))
    }


    @Test
    fun `run should throw an IllegalArgumentException if the appointment is not found`() {
        val appointmentRepository = mock<AppointmentRepository>()
        val updateAppointmentStatusUseCase = UpdateAppointmentStatusUseCase(appointmentRepository)

        val appointmentId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c2")
        val newStatus = AppointmentStatus.CANCELLED

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            updateAppointmentStatusUseCase.run(
                appointmentId.value,
                newStatus,
            )
        }.withMessage("Appointment with id 6c26cc8b-6f02-4e06-8c02-a070388934c2 not found")
    }

    @Test
    fun `run should throw an IllegalArgumentException if the new status is OPEN`() {
        val appointment = Appointment(
            id = appointmentId,
            patient = patient,
            doctorId = doctorId,
            slotId = slotId,
            startTimeEpoch = startTimeEpoch,
            endTimeEpoch = endTimeEpoch,
            status = AppointmentStatus.COMPLETED,
        )
        val appointmentRepository = mock<AppointmentRepository>()
        whenever(appointmentRepository.findById(appointment.id.value)).thenReturn(appointment)

        val updateAppointmentStatusUseCase = UpdateAppointmentStatusUseCase(appointmentRepository)

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            updateAppointmentStatusUseCase.run(
                appointmentId.value,
                AppointmentStatus.OPENED,
            )
        }
            .withMessage("Appointment with id 6c26cc8b-6f02-4e06-8c02-a070388934c2 can only be OPEN once, use REOPEN instead")
    }
}
