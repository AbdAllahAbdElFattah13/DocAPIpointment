package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.ViewAllDoctorsAvailableSlotsResponse
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.mappers.DTOMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.ShowAvailableSlotsUseCase
import me.abdallah_abdelfattah.DocAPIpointment.shared.apis.Constants
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constants.API_VERSION}/availableSlots")
class ViewAllDoctorsAvailableSlotsController(
    private val useCase: ShowAvailableSlotsUseCase,
    private val mapper: DTOMapper,
) {

    @GetMapping
    fun viewAllDoctorsAvailableSlots(): ViewAllDoctorsAvailableSlotsResponse {
        return ViewAllDoctorsAvailableSlotsResponse(
            slots = useCase.run().map(mapper::mapToResponseSlot)
        )
    }

}
