package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.controllers.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.api.dto.AppointmentResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface AppointmentOperationsDTOMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = ["mapStatusInResponse"])
    fun toAppointmentResponse(appointmentView: AppointmentView): AppointmentResponse

    fun toAppointmentResponses(appointmentViews: List<AppointmentView>): List<AppointmentResponse>

    @Named("mapStatusInResponse")
    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    fun mapStatusInResponse(status: String): AppointmentStatusView

    @Named("mapStatusInRequest")
    @EnumMapping(nameTransformationStrategy = "case", configuration = "lower")
    @ValueMapping(source = "opened", target = MappingConstants.THROW_EXCEPTION)
    @ValueMapping(source = MappingConstants.ANY_REMAINING, target = MappingConstants.THROW_EXCEPTION)
    fun mapStatusInRequest(status: String): AppointmentStatusView

}
