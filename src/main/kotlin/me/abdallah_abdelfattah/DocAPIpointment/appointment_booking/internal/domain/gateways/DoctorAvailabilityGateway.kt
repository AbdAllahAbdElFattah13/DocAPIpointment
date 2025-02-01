package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.SlotView

interface DoctorAvailabilityGateway {
    fun getAllDoctorsAvailableSlots(): List<SlotView>
    fun getSlotById(slotId: String): SlotView?
    fun reserveSlot(slotId: String): Boolean
}
