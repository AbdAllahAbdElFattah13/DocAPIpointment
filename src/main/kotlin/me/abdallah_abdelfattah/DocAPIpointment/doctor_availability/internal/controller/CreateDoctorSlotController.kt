package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.Constants
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.CreateDoctorSlotRequest
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.service.DoctorAvailabilityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constants.API_VERSION}/slots/{doctorId}")
class CreateDoctorSlotController(
    private val service: DoctorAvailabilityService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createDoctorSlot(
        @PathVariable doctorId: String,
        @RequestBody request: CreateDoctorSlotRequest,
    ) {

        service.addDoctorSlot(
            doctorId = doctorId,
            slotStartDateTimeValue = request.slotStartDateTime,
            costValue = request.cost,
        )

    }

}
