package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class AppointmentStatusHandlerTest {

    @Test
    fun `assert that CompletedAppointmentStatusHandler marks appointment as complete`() {
        val gateway: AppointmentBookingGateway = mock()
        val handler = CompletedAppointmentStatusHandler(gateway)
        val appointmentId = "appointmentId"

        handler.handleAppointmentStatus(appointmentId)

        verify(gateway, times(1)).markAppointmentAsComplete(appointmentId)
    }

    @Test
    fun `assert that CancelledAppointmentStatusHandler cancels appointment`() {
        val gateway: AppointmentBookingGateway = mock()
        val handler = CancelledAppointmentStatusHandler(gateway)
        val appointmentId = "appointmentId"

        handler.handleAppointmentStatus(appointmentId)

        verify(gateway, times(1)).cancelAppointment(appointmentId)
    }

    @Test
    fun `assert that ReopenedAppointmentStatusHandler reopens appointment`() {
        val gateway: AppointmentBookingGateway = mock()
        val handler = ReopenedAppointmentStatusHandler(gateway)
        val appointmentId = "appointmentId"

        handler.handleAppointmentStatus(appointmentId)

        verify(gateway, times(1)).reopenAppointment(appointmentId)
    }

}
