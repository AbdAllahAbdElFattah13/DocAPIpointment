package me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.appointment_operations

import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.appointment_operations.AppointmentOperationsAdapter
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentStatusView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.core.models.AppointmentView
import me.abdallah_abdelfattah.DocAPIpointment.doctor_appointment_management.internal.shell.gateways.AppointmentBookingGateway
import me.abdallah_abdelfattah.DocAPIpointment.shared.nowEpoch
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class AppointmentOperationsAdapterTest {

    @Test
    fun `assert that the upcoming appointments are fetched correctly`() {
        val doctorId = "doctorId"
        val clock = Clock.fixed(
            Instant.ofEpochMilli(nowEpoch),
            ZoneOffset.UTC
        )
        val appointmentBookingGateway: AppointmentBookingGateway = mock()
        val appointmentStatusHandlers = mapOf<AppointmentStatusView, AppointmentStatusHandler>()
        val appointmentView: AppointmentView = mock()

        val appointmentOperationsAdapter = AppointmentOperationsAdapter(
            appointmentBookingGateway,
            appointmentStatusHandlers,
            clock,
        )
        val expectedAppointments = listOf(appointmentView)
        whenever(appointmentBookingGateway.getUpcomingAppointments(doctorId, nowEpoch))
            .thenReturn(expectedAppointments)

        val actualAppointments = appointmentOperationsAdapter.getUpcomingAppointments(doctorId)

        verify(appointmentBookingGateway, times(1)).getUpcomingAppointments(doctorId, nowEpoch)
        assertThat(expectedAppointments)
            .containsExactly(*actualAppointments.toTypedArray())

    }

    @Test
    fun `assert that the appointment status is updated correctly`() {
        val appointmentId = "appointmentId"
        val status = AppointmentStatusView.CANCELLED
        val appointmentStatusHandler: AppointmentStatusHandler = mock()
        val appointmentStatusHandlers = mapOf(status to appointmentStatusHandler)
        val appointmentBookingGateway: AppointmentBookingGateway = mock()
        val clock = Clock.fixed(
            Instant.ofEpochMilli(nowEpoch),
            ZoneOffset.UTC
        )

        val appointmentOperationsAdapter = AppointmentOperationsAdapter(
            appointmentBookingGateway,
            appointmentStatusHandlers,
            clock,
        )

        appointmentOperationsAdapter.updateAppointmentStatus(appointmentId, status)

        verify(appointmentStatusHandler, times(1)).handleAppointmentStatus(appointmentId)
    }

    @Test
    fun `assert that an exception is thrown when the status is invalid`() {
        val appointmentId = "appointmentId"
        val status = AppointmentStatusView.CANCELLED
        val appointmentStatusHandlers = mapOf<AppointmentStatusView, AppointmentStatusHandler>()
        val appointmentBookingGateway: AppointmentBookingGateway = mock()
        val clock = Clock.fixed(
            Instant.ofEpochMilli(nowEpoch),
            ZoneOffset.UTC
        )

        val appointmentOperationsAdapter = AppointmentOperationsAdapter(
            appointmentBookingGateway,
            appointmentStatusHandlers,
            clock,
        )


        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy {
                appointmentOperationsAdapter.updateAppointmentStatus(appointmentId, status)
            }
    }

}
