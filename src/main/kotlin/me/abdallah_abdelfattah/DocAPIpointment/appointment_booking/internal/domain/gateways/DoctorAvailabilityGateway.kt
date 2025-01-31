package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO

interface DoctorAvailabilityGateway {
    fun getAllDoctorsAvailableSlots(): List<SlotDTO>
    fun getSlotById(slotId: String): SlotDTO?
    fun reserveSlot(slotId: String): Boolean
}
