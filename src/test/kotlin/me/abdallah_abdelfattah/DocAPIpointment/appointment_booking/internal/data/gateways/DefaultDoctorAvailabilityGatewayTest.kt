package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways.mappers.DoctorAvailabilityModelsMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DefaultDoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.SlotView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DoctorAvailabilityAPI
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DefaultDoctorAvailabilityGatewayTest {

    private val api = mock<DoctorAvailabilityAPI>()
    private val slotDTO: SlotDTO = mock()
    private val slotView: SlotView = mock()
    private val mapper: DoctorAvailabilityModelsMapper = mock {
        on { mapToSlot(slotDTO) } doReturn slotView
    }

    private val gateway = DefaultDoctorAvailabilityGateway(api, mapper)

    @BeforeEach
    fun setUp() {
        reset(api)
    }

    @Test
    fun `getAllDoctorsAvailableSlots should return the same results as provided by API`() {
        val expected: List<SlotView> = listOf(slotView)
        whenever(api.getAllDoctorsAvailability()).thenReturn(listOf(slotDTO))

        val result = gateway.getAllDoctorsAvailableSlots()

        verify(api, times(1)).getAllDoctorsAvailability()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `getSlotById should return return the same results as provided by API`() {
        val slotId = "slotId"
        whenever(api.getSlotById(slotId)).thenReturn(slotDTO)

        val result = gateway.getSlotById(slotId)

        verify(api, times(1)).getSlotById(slotId)
        assertThat(result).isEqualTo(slotView)

    }

    @Test
    fun `reserveSlot should return the same results as provided by API`() {
        val slotId = "slotId"
        val expected = true
        whenever(api.reserveSlot(slotId)).thenReturn(expected)

        val result = gateway.reserveSlot(slotId)

        verify(api, times(1)).reserveSlot(slotId)
        assertThat(result).isEqualTo(expected)
    }
}
