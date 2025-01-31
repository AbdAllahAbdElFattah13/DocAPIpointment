package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Slot
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
)
interface DTOMapper {
    fun mapToResponseSlot(slot: Slot): ResponseSlot
    fun mapToResponseSlots(slots: List<Slot>): List<ResponseSlot>

    fun mapToSlot(responseSlot: ResponseSlot): Slot
    fun mapToSlots(responseSlots: List<ResponseSlot>): List<Slot>
}
