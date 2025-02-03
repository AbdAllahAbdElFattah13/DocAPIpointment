package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways


import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface AppointmentGatewayMapper {
    fun toAppointmentView(appointmentDTO: AppointmentDTO): AppointmentView
}
