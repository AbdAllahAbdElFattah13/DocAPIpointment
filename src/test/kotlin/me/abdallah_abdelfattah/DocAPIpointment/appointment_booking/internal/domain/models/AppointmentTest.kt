package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class AppointmentTest {

    @Test
    fun `cancel appointment`() {
        val appointment = Appointment(
            doctorId = GUID(),
            slotId = GUID(),
            patient = Patient(name = Name("John Doe")),
        )

        val cancelledAppointment = appointment.cancel()

        assertThat(cancelledAppointment.status)
            .isEqualTo(AppointmentStatus.CANCELLED)
        assertThat(cancelledAppointment.lastUpdatedAtEpoch).isNotNull
    }

    @Test
    fun `confirm appointment`() {
        val appointment = Appointment(
            doctorId = GUID(),
            slotId = GUID(),
            patient = Patient(name = Name("John Doe")),
        )

        val confirmedAppointment = appointment.confirm()

        assertThat(confirmedAppointment.status)
            .isEqualTo(AppointmentStatus.CONFIRMED)
        assertThat(confirmedAppointment.lastUpdatedAtEpoch).isNotNull
    }

    @Test
    fun `complete appointment`() {
        val appointment = Appointment(
            doctorId = GUID(),
            slotId = GUID(),
            patient = Patient(name = Name("John Doe")),
        )

        val completedAppointment = appointment.complete()

        assertThat(completedAppointment.status)
            .isEqualTo(AppointmentStatus.COMPLETED)
        assertThat(completedAppointment.lastUpdatedAtEpoch).isNotNull
    }
}
