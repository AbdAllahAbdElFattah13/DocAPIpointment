package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO
import me.abdallah_abdelfattah.DocAPIpointment.shared.SharedModelsMapping
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(
    componentModel = "spring",
    uses = [SharedModelsMapping::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
)
interface AppointmentMapper {
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName", source = "patient.name")
    @Mapping(target = "status", source = "status", qualifiedByName = ["mapStatus"])
    fun toAppointmentDTO(appointment: Appointment): AppointmentDTO

    @Named("mapStatus")
    fun mapStatus(status: AppointmentStatus): String = status.name
}
