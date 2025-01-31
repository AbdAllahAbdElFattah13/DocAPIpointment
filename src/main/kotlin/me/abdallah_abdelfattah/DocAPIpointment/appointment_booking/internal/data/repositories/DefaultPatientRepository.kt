package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.DEFAULT_Patient
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.PatientRepository
import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.Logger
import org.springframework.stereotype.Repository

@Repository
class DefaultPatientRepository(
    private val logger: Logger,
) : PatientRepository {

    private val patients = mutableListOf<Patient>()

    init {
        logger.info("Adding default patient $DEFAULT_Patient")
        patients.add(DEFAULT_Patient)
    }


    override fun save(patient: Patient) {
        patients.add(patient)
    }

    override fun findById(id: String): Patient? {
        return patients.find { it.id.value == id }
    }
}
