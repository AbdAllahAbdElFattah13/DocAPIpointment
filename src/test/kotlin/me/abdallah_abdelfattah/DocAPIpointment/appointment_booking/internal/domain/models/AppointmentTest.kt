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
            startTimeEpoch = 1,
            endTimeEpoch = 2,
        )

        val cancelledAppointment = appointment.cancel()

        assertThat(cancelledAppointment.status)
            .isEqualTo(AppointmentStatus.CANCELLED)
        assertThat(cancelledAppointment.lastUpdatedAtEpoch).isNotNull
    }

    @Test
    fun `complete appointment`() {
        val appointment = Appointment(
            doctorId = GUID(),
            slotId = GUID(),
            patient = Patient(name = Name("John Doe")),
            startTimeEpoch = 1,
            endTimeEpoch = 2,
        )

        val completedAppointment = appointment.complete()

        assertThat(completedAppointment.status)
            .isEqualTo(AppointmentStatus.COMPLETED)
        assertThat(completedAppointment.lastUpdatedAtEpoch).isNotNull
    }

    @Test
    fun `reopen appointment`() {
        val appointment = Appointment(
            doctorId = GUID(),
            slotId = GUID(),
            patient = Patient(name = Name("John Doe")),
            startTimeEpoch = 1,
            endTimeEpoch = 2,
        )

        val reopenedAppointment = appointment.reopen()

        assertThat(reopenedAppointment.status)
            .isEqualTo(AppointmentStatus.OPENED)
        assertThat(reopenedAppointment.lastUpdatedAtEpoch).isNotNull
    }
}
