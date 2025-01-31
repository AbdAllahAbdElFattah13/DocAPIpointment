package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DefaultDoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DoctorAvailabilityAPI
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DefaultDoctorAvailabilityGatewayTest {

    private val api = mock<DoctorAvailabilityAPI>()
    private val gateway = DefaultDoctorAvailabilityGateway(api)

    @BeforeEach
    fun setUp() {
        reset(api)
    }

    @Test
    fun `getAllDoctorsAvailableSlots should return the same results as provided by API`() {
        val expected: List<SlotDTO> = mock()
        whenever(api.getAllDoctorsAvailability()).thenReturn(expected)

        val result = gateway.getAllDoctorsAvailableSlots()

        verify(api, times(1)).getAllDoctorsAvailability()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `getSlotById should return return the same results as provided by API`() {
        val slotId = "slotId"
        val expected: SlotDTO = mock()
        whenever(api.getSlotById(slotId)).thenReturn(expected)

        val result = gateway.getSlotById(slotId)

        verify(api, times(1)).getSlotById(slotId)
        assertThat(result).isEqualTo(expected)

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
