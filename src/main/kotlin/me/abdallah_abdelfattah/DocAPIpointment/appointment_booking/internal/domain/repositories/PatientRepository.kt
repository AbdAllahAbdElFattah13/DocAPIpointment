package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient

interface PatientRepository {
    fun save(patient: Patient)
    fun findById(id: String): Patient?
}
