package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.AppointmentResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ValueMapping

@Mapper(componentModel = "spring")
interface AppointmentOperationsDTOMapper {

    fun toAppointmentResponse(appointmentView: AppointmentView): AppointmentResponse

    fun toAppointmentResponses(appointmentViews: List<AppointmentView>): List<AppointmentResponse>

    @ValueMapping(source = "OPENED", target = "OPENED")
    @ValueMapping(source = "REOPENED", target = "REOPENED")
    @ValueMapping(source = "COMPLETED", target = "COMPLETED")
    @ValueMapping(source = "CANCELLED", target = "CANCELLED")
    @ValueMapping(source = MappingConstants.ANY_REMAINING, target = MappingConstants.THROW_EXCEPTION)
    fun mapStatus(status: String): AppointmentStatusView

}
