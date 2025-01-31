package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DefaultAppointmentRepositoryTest {

    private val patient = Patient(
        id = GUID(value = "6c26cc8b-6f02-4e06-8c02-a070388934c5"),
        name = Name("Mohamed El-Shazly"),
    )
    private val doctorId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c4")
    private val slotId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c3")
    private val appointmentId = GUID("6c26cc8b-6f02-4e06-8c02-a070388934c2")

    @Test
    fun `should save the appointment`() {
        val appointment = Appointment(
            id = appointmentId,
            patient = patient,
            doctorId = doctorId,
            slotId = slotId,
        )
        val repository = DefaultAppointmentRepository()

        repository.save(appointment)

        val savedAppointment = repository.findById(appointmentId.value)
        savedAppointment!!
        assertThat(savedAppointment).isEqualTo(appointment)
        assertThat(savedAppointment.createdAtEpoch).isNotNull()
    }

    @Test
    fun `findById will return null if the appointment is not found`() {
        val repository = DefaultAppointmentRepository()

        val savedAppointment = repository.findById("none existing id")

        assertThat(savedAppointment).isNull()
    }

    @Test
    fun `should update the appointment`() {
        val appointment = Appointment(
            id = appointmentId,
            patient = patient,
            doctorId = doctorId,
            slotId = slotId,
        )
        val repository = DefaultAppointmentRepository()

        repository.save(appointment)

        val newAppointment = appointment.copy(patient = patient.copy(name = Name("Mohamed El-Shazly 2")))
        repository.update(appointmentId.value, newAppointment)

        val updatedAppointment = repository.findById(newAppointment.id.value)

        updatedAppointment!!

        assertThat(updatedAppointment).isEqualTo(newAppointment)

    }
}
