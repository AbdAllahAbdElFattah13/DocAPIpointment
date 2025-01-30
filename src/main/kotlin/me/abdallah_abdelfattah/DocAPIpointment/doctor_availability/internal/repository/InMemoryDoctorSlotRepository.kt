package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownDoctorException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.DEFAULT_DOCTOR
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Doctor
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.Logger
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.springframework.stereotype.Repository

@Repository
class InMemoryDoctorSlotRepository(
    private val logger: Logger,
)
    : DoctorSlotRepository{
        private val doctors = mutableListOf<Doctor>()
        private val slots = mutableListOf<Slot>()

    init {
        logger.info("Adding default doctor $DEFAULT_DOCTOR")
        doctors.add(DEFAULT_DOCTOR)
    }

    override fun getDoctor(doctorId: GUID): Doctor {
        return doctors.firstOrNull { it.id == doctorId } ?: throw UnknownDoctorException(doctorId)
    }

    override fun getDoctorAvailability(doctor: Doctor): List<Slot> {
        return slots.filter { it.doctorId == doctor.id }
    }

    override fun addDoctorSlot(slot: Slot) {
        slots.add(slot)
    }
}
