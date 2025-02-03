package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations.AppointmentOperationsPort
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.dto.UpdateAppointmentStatusRequest
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.mappers.AppointmentOperationsDTOMapper
import me.abdallah_abdelfattah.DocAPIpointment.shared.apis.Constants
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("${Constants.API_VERSION}/appointment/status")
class UpdateAppointmentStatusController(
    private val appointmentOperations: AppointmentOperationsPort,
    private val mapper: AppointmentOperationsDTOMapper,
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun book(
        @RequestBody request: UpdateAppointmentStatusRequest
    ) {
        appointmentOperations.updateAppointmentStatus(
            request.appointmentId,
            mapper.mapStatus(request.appointmentStatus)
        )
    }
}
