package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
)
interface DoctorAvailabilityModelsMapper {
    fun mapToSlotDTO(slot: Slot): SlotDTO
    fun mapToSlotsDTO(slots: List<Slot>): List<SlotDTO>

    fun mapToSlot(slotDTO: SlotDTO): Slot
    fun mapToSlots(slotsDTO: List<SlotDTO>): List<Slot>
}
