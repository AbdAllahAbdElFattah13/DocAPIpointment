package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.Constants
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.GetDoctorAvailabilityServiceResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.service.DoctorAvailabilityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("${Constants.API_VERSION}/slots/{doctorId}")
class ListDoctorSlotsController(
    private val service: DoctorAvailabilityService
) {

    @GetMapping
    fun getDoctorSlot(
        @PathVariable doctorId: String
    ): GetDoctorAvailabilityServiceResponse {
        return service.getDoctorAvailability(
            doctorId = doctorId
        )
    }

}
