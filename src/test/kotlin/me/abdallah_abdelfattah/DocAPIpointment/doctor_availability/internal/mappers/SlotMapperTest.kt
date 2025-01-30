package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDateEpoch
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.nowEpoch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant

class SlotMapperTest {

    private val clock = mock<Clock> {
        on { instant() } doReturn Instant.ofEpochMilli(nowEpoch)
    }

    @Test
    fun `assert slot to response slot mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            durationInMinutes = 30,
            isReserved = false,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.epochMillis.toString(),
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
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            durationInMinutes = 30,
            isReserved = false,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.epochMillis.toString(),
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
