package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.configs


import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.CancelledAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.CompletedAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations.ReopenedAppointmentStatusHandler
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import java.time.ZoneOffset

class AppointmentModuleConfigTest {

    @Test
    fun `clock should return system UTC clock`() {
        val clock = AppointmentModuleConfig().clock()
        assertThat(clock).isNotNull
        assertThat(clock.zone).isEqualTo(ZoneOffset.UTC)
    }

    @Test
    fun `appointmentStatusHandlers should return a map of appointment status handlers`() {
        val gateway: AppointmentBookingGateway = mock()
        val appointmentStatusHandlers = AppointmentModuleConfig().appointmentStatusHandlers(gateway)
        assertThat(appointmentStatusHandlers).isNotNull
        assertThat(appointmentStatusHandlers).containsKeys(
            AppointmentStatusView.COMPLETED,
            AppointmentStatusView.CANCELLED,
            AppointmentStatusView.REOPENED
        )
        assertThat(appointmentStatusHandlers[AppointmentStatusView.COMPLETED])
            .isInstanceOf(CompletedAppointmentStatusHandler::class.java)
        assertThat(appointmentStatusHandlers[AppointmentStatusView.CANCELLED])
            .isInstanceOf(CancelledAppointmentStatusHandler::class.java)
        assertThat(appointmentStatusHandlers[AppointmentStatusView.REOPENED])
            .isInstanceOf(ReopenedAppointmentStatusHandler::class.java)
    }
}
