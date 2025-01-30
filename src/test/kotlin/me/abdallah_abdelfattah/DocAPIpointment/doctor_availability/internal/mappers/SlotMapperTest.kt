package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SlotMapperTest {

    @Test
    fun `assert slot to response slot mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = futureDate,
            durationInMinutes = 30,
            isReserved = false,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.dateTimeString,
            durationInMinutes = slot.durationInMinutes,
            isReserved = slot.isReserved,
            cost = slot.cost.value,
        )

        assertEquals(responseSlot, SlotMapper().toResponseSlot(slot))
    }

    @Test
    fun `assert list slot to list of response slot mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = futureDate,
            durationInMinutes = 30,
            isReserved = false,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.dateTimeString,
            durationInMinutes = slot.durationInMinutes,
            isReserved = slot.isReserved,
            cost = slot.cost.value,
        )

        assertEquals(
            listOf(responseSlot),
            SlotMapper().toResponseSlots(listOf(slot))
        )
    }

}
