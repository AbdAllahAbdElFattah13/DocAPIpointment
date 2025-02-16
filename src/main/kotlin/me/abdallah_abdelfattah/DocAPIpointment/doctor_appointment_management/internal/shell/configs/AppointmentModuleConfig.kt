package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.configs

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.AppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.CancelledAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.CompletedAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.ReopenedAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class AppointmentModuleConfig {

    @Bean
    fun clock(): Clock = Clock.systemUTC()

    @Bean
    fun appointmentStatusHandlers(gateway: AppointmentBookingGateway): Map<AppointmentStatusView, AppointmentStatusHandler> =
        mapOf(
            AppointmentStatusView.COMPLETED to CompletedAppointmentStatusHandler(gateway),
            AppointmentStatusView.CANCELLED to CancelledAppointmentStatusHandler(gateway),
            AppointmentStatusView.REOPENED to ReopenedAppointmentStatusHandler(gateway),
        )
}
