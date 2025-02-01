package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.SlotView
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
)
interface DTOMapper {
    fun mapToResponseSlot(slot: SlotView): ResponseSlot
    fun mapToResponseSlots(slots: List<SlotView>): List<ResponseSlot>

    fun mapToSlot(responseSlot: ResponseSlot): SlotView
    fun mapToSlots(responseSlots: List<ResponseSlot>): List<SlotView>
}
