package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import org.springframework.stereotype.Component

@Component
class SlotMapper {

    fun toResponseSlot(slot: Slot): ResponseSlot {
        return ResponseSlot(
            id=slot.id.value,
            time=slot.time.epochMillis.toString(),
            durationInMinutes = slot.durationInMinutes,
            isReserved = slot.isReserved,
            cost = slot.cost.value,
        )
    }

    fun toResponseSlots(slots: List<Slot>): List<ResponseSlot> {
        return slots.map { toResponseSlot(it) }
    }

}
