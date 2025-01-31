package me.abdallah_abdelfattah.DocAPIpointment.shared.apis

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnavailableSlotException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownDoctorException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownSlotException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(value = [
        IllegalArgumentException::class,
        UnknownDoctorException::class,
        UnknownSlotException::class,
        UnavailableSlotException.SlotAlreadyExistException::class,
        UnavailableSlotException.SlotOverlapAnotherSlotException::class,
        UnavailableSlotException.SlotAlreadyReservedException::class,
        UnknownSlotException::class,
    ])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestExceptions(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}
