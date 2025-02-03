package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.ViewAllDoctorUpcomingAppointmentsResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations.AppointmentOperationsPort
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.mappers.AppointmentOperationsDTOMapper
import me.abdallah_abdelfattah.DocAPIpointment.shared.apis.Constants
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("${Constants.API_VERSION}/doctor/{doctorId}/upcoming_appointments")
class ViewAllDoctorUpcomingAppointmentsController(
    private val appointmentOperationsPort: AppointmentOperationsPort,
    private val mapper: AppointmentOperationsDTOMapper,
) {

    @GetMapping
    fun viewAllDoctorUpcomingAppointments(
        @PathVariable doctorId: String
    ): ViewAllDoctorUpcomingAppointmentsResponse {
        return ViewAllDoctorUpcomingAppointmentsResponse(
            appointments = appointmentOperationsPort.getUpcomingAppointments(doctorId)
                .map(mapper::toAppointmentResponse)
        )
    }

}
