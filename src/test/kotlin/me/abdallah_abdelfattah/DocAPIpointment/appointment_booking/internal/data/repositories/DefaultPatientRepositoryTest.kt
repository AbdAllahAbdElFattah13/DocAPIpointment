package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.DEFAULT_Patient
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class DefaultPatientRepositoryTest {

    @Test
    fun `should contain a default patient`() {
        val patientRepository = DefaultPatientRepository(mock())

        val patient = patientRepository.findById(DEFAULT_Patient.id.value)

        assertThat(patient).isEqualTo(DEFAULT_Patient)
    }

    @Test
    fun `should save a patient`() {
        val patientRepository = DefaultPatientRepository(mock())

        val patientId = GUID()
        val patient = Patient(
            id = patientId,
            name = Name("Another Patient"),
        )

        patientRepository.save(patient)

        val savedPatient = patientRepository.findById(patient.id.value)

        assertThat(savedPatient).isEqualTo(patient)
    }
}
