package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import me.abdallah_abdelfattah.DocAPIpointment.shared.cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDateEpoch
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.SharedModelsMapping
import me.abdallah_abdelfattah.DocAPIpointment.shared.nowEpoch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant

class SlotMapperTest {

    private val slotMapper: SlotMapper = SlotMapperImpl(
        SharedModelsMapping(),
        CostMapper()
    )
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
            reserved = true,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.epochMillis.toString(),
            durationInMinutes = slot.durationInMinutes,
            reserved = slot.reserved,
            cost = slot.cost.value,
        )

        assertEquals(responseSlot, slotMapper.toResponseSlot(slot))
    }

    @Test
    fun `assert list slot to list of response slot mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            durationInMinutes = 30,
            reserved = false,
            cost = cost,
        )

        val responseSlot = ResponseSlot(
            id = slot.id.value,
            time = slot.time.epochMillis.toString(),
            durationInMinutes = slot.durationInMinutes,
            reserved = slot.reserved,
            cost = slot.cost.value,
        )

        assertEquals(
            listOf(responseSlot),
            slotMapper.toResponseSlots(listOf(slot))
        )
    }

    @Test
    fun `assert slot to slot dto mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            durationInMinutes = 30,
            reserved = true,
            cost = cost,
        )

        val slotDTO = SlotDTO(
            id = slot.id.value,
            doctorId = slot.doctorId.value,
            startTimeEpoch = slot.time.epochMillis,
            endTimeEpoch = slot.time.epochMillis + slot.durationInMinutes * 60 * 1000,
            reserved = slot.reserved,
            cost = slot.cost.value,
        )

        assertEquals(slotDTO, slotMapper.toSlotDTO(slot))
    }

    @Test
    fun `assert list slot to list of slot dto mapping`() {
        val slot = Slot(
            id = GUID(),
            doctorId = GUID(),
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            durationInMinutes = 30,
            reserved = false,
            cost = cost,
        )

        val slotDTO = SlotDTO(
            id = slot.id.value,
            doctorId = slot.doctorId.value,
            startTimeEpoch = slot.time.epochMillis,
            endTimeEpoch = slot.time.epochMillis + slot.durationInMinutes * 60 * 1000,
            reserved = slot.reserved,
            cost = slot.cost.value,
        )

        assertEquals(
            listOf(slotDTO),
            slotMapper.toSlotsDTO(listOf(slot))
        )
    }

}
