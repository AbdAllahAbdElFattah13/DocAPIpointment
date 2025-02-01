package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers.DoctorAvailabilityModelsMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.SlotView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class ShowAvailableSlotsUseCaseTest {

    @Test
    fun `should return all available slots`() {

        val slotDTO = mock<SlotDTO>()
        val slot = mock<SlotView>()

        val slotsDTOs: List<SlotDTO> = listOf(slotDTO)
        val slots: List<SlotView> = listOf(slot)

        val doctorAvailabilityGateway: DoctorAvailabilityGateway = mock {
            on(it.getAllDoctorsAvailableSlots()) doReturn slotsDTOs
        }
        val doctorAvailabilityMapper: DoctorAvailabilityModelsMapper = mock {
            on(it.mapToSlot(slotDTO)) doReturn slot
        }
        val showAvailableSlotsUseCase = ShowAvailableSlotsUseCase(
            doctorAvailabilityGateway,
            doctorAvailabilityMapper,
        )

        val result = showAvailableSlotsUseCase.run()

        assertThat(result)
            .isEqualTo(slots)
    }

}
