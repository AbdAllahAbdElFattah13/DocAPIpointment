package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers.SlotMapper
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository.DoctorSlotRepository
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DefaultDoctorAvailabilityAPITest {

    private val slot: Slot = mock()
    private val slotDTO: SlotDTO = mock()

    private val doctorSlotRepository: DoctorSlotRepository = mock()
    private val slotMapper: SlotMapper = mock {
        on { toSlotDTO(slot) } doReturn slotDTO
    }
    private val defaultDoctorAvailabilityAPI = DefaultDoctorAvailabilityAPI(
        doctorSlotRepository,
        slotMapper,
    )

    @BeforeEach
    fun setup() {
        reset(doctorSlotRepository)
    }

    @Test
    fun `getAllDoctorsAvailability returns the repo equivalent mapped to dto`() {
        val expectedSlots = listOf(slotDTO)
        whenever(doctorSlotRepository.getAllDoctorsAvailability()).thenReturn(listOf(slot))
        val slots = defaultDoctorAvailabilityAPI.getAllDoctorsAvailability()
        assertThat(slots).isEqualTo(expectedSlots)
    }

    @Test
    fun `getSlotById returns the repo equivalent mapped to dto`() {
        val slotId = "slotId"
        whenever(doctorSlotRepository.getSlotById(slotId)).thenReturn(slot)
        val expectedSlotDTO = defaultDoctorAvailabilityAPI.getSlotById(slotId)
        assertThat(expectedSlotDTO).isEqualTo(slotDTO)
    }

    @Test
    fun `reserveSlot returns true if slot exists and is not reserved`() {
        val slotId = "slotId"
        whenever(doctorSlotRepository.getSlotById(slotId)).thenReturn(slot)
        val result = defaultDoctorAvailabilityAPI.reserveSlot(slotId)
        assertThat(result).isTrue()
        verify(doctorSlotRepository).updateSlot(slotId, slot.copy(reserved = true))
    }

    @Test
    fun `reserveSlot returns false if slot does not exist`() {
        val slotId = "slotId"
        whenever(doctorSlotRepository.getSlotById(slotId)).thenReturn(null)
        val result = defaultDoctorAvailabilityAPI.reserveSlot(slotId)
        assertThat(result).isFalse()
    }

    @Test
    fun `reserveSlot returns false if slot is already reserved`() {
        val slotId = "slotId"
        val reservedSlot: Slot = mock()
        whenever(reservedSlot.reserved).thenReturn(true)
        whenever(doctorSlotRepository.getSlotById(slotId)).thenReturn(reservedSlot)
        val result = defaultDoctorAvailabilityAPI.reserveSlot(slotId)
        assertThat(result).isFalse()
    }

}
