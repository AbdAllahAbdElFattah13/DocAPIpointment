package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.DoctorDTO
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO

interface DoctorAvailabilityAPI {
    fun getAllDoctorsAvailability(): List<SlotDTO>
    fun getSlotById(slotId: String): SlotDTO?
    fun reserveSlot(slotId: String): Boolean
    fun getDoctorInfo(doctorId: String): DoctorDTO?
}
