package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.api.DefaultAppointmentBookingAPI
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.shared.dto.AppointmentDTO
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DefaultAppointmentBookingGatewayTest {

    @Test
    fun `getUpcomingAppointments should return a list of appointments`() {
        val api: DefaultAppointmentBookingAPI = mock()
        val mapper: AppointmentGatewayMapper = mock()
        val nowEpochMillis = 123456L
        val gateway = DefaultAppointmentBookingGateway(
            api,
            mapper,
        )
        val doctorId = "doctorId"
        val appointmentView: AppointmentView = mock()
        val appointmentDTO: AppointmentDTO = mock()
        whenever(api.getUpcomingAppointments(doctorId, nowEpochMillis)) doReturn (listOf(appointmentDTO))
        whenever(mapper.toAppointmentView(appointmentDTO)) doReturn (appointmentView)

        val result = gateway.getUpcomingAppointments(doctorId, nowEpochMillis)

        assertThat(result).containsExactly(appointmentView)
    }

    @Test
    fun `markAppointmentAsComplete should call api`() {
        val api: DefaultAppointmentBookingAPI = mock()
        val mapper: AppointmentGatewayMapper = mock()
        val gateway = DefaultAppointmentBookingGateway(
            api,
            mapper,
        )
        val appointmentId = "appointmentId"

        gateway.markAppointmentAsComplete(appointmentId)

        verify(api, times(1)).markAppointmentAsComplete(appointmentId)
    }

    @Test
    fun `cancelAppointment should call api`() {
        val api: DefaultAppointmentBookingAPI = mock()
        val mapper: AppointmentGatewayMapper = mock()
        val gateway = DefaultAppointmentBookingGateway(
            api,
            mapper,
        )
        val appointmentId = "appointmentId"

        gateway.cancelAppointment(appointmentId)

        verify(api, times(1)).cancelAppointment(appointmentId)
    }

    @Test
    fun `reopenAppointment should call api`() {
        val api: DefaultAppointmentBookingAPI = mock()
        val mapper: AppointmentGatewayMapper = mock()
        val gateway = DefaultAppointmentBookingGateway(
            api,
            mapper,
        )
        val appointmentId = "appointmentId"

        gateway.reopenAppointment(appointmentId)

        verify(api, times(1)).reopenAppointment(appointmentId)
    }

}
