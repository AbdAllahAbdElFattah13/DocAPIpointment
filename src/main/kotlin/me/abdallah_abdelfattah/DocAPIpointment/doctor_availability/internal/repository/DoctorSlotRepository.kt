package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Doctor
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID

interface DoctorSlotRepository {
    fun getDoctor(doctorId: GUID): Doctor
    fun getDoctorAvailability(doctor: Doctor): List<Slot>
    fun addDoctorSlot(slot: Slot)
    fun getAllDoctorsAvailability(): List<Slot>
    fun getSlotById(slotId: String): Slot?
    fun updateSlot(slotId: String, slot: Slot)
}
