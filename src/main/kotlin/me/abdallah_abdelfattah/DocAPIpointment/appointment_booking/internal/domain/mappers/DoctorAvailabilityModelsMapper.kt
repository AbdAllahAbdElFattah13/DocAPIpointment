package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.SlotView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
)
interface DoctorAvailabilityModelsMapper {
    fun mapToSlotDTO(slot: SlotView): SlotDTO
    fun mapToSlotsDTO(slots: List<SlotView>): List<SlotDTO>

    fun mapToSlot(slotDTO: SlotDTO): SlotView
    fun mapToSlots(slotsDTO: List<SlotDTO>): List<SlotView>
}
