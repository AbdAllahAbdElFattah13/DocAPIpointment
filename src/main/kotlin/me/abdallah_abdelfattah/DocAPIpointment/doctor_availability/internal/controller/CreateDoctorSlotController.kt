package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.Constants
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(Constants.SLOTS_ENDPOINT)
class CreateDoctorSlotController {

    @GetMapping("/create")
    fun create(): String {
        return "created"
    }

}
