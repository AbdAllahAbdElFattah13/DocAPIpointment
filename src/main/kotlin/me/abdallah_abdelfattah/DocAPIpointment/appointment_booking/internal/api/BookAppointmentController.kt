package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.BookAppointmentRequest
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases.BookSlotUseCase
import me.abdallah_abdelfattah.DocAPIpointment.shared.apis.Constants
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("${Constants.API_VERSION}/appointment")
class BookAppointmentController(
    private val useCase: BookSlotUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun book(
        @RequestBody request: BookAppointmentRequest
    ) {
        useCase.run(request.patientId, request.slotId)
    }
}
