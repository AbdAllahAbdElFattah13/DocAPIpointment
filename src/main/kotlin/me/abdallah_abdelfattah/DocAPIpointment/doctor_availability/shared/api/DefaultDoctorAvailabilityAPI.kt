package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers.SlotMapper
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository.DoctorSlotRepository
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.springframework.stereotype.Component

@Component
class DefaultDoctorAvailabilityAPI(
    private val doctorSlotRepository: DoctorSlotRepository,
    private val slotMapper: SlotMapper,
) : DoctorAvailabilityAPI {
    override fun getAllDoctorsAvailability() =
        doctorSlotRepository.getAllDoctorsAvailability().map(slotMapper::toSlotDTO)

    override fun getSlotById(slotId: String): SlotDTO? =
        doctorSlotRepository.getSlotById(slotId)?.let(slotMapper::toSlotDTO)

    override fun reserveSlot(slotId: String): Boolean {
        val slot = doctorSlotRepository.getSlotById(slotId) ?: return false
        if (slot.reserved) return false
        doctorSlotRepository.updateSlot(slotId, slot.copy(reserved = true))
        return true
    }
}
