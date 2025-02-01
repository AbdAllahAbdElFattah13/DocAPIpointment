package me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.api

import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender.AppointmentConfirmationNotification
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.internal.notificaion_sender.NotificationSender
import me.abdallah_abdelfattah.DocAPIpointment.appointment_confirmation.shared.requests.AppointmentConfirmationNotificationRequest
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DefaultDoctorAvailabilityAPI
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.DoctorDTO
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class DefaultNotificationAPITest {

    private val notificationSender: NotificationSender = mock()
    private val doctorAvailabilityAPI: DefaultDoctorAvailabilityAPI = mock()

    @BeforeEach
    fun setUp() {
        reset(
            notificationSender,
            doctorAvailabilityAPI,
        )
    }

    @Test
    fun `sendAppointmentNotification should send notification`() {
        val doctorId = "doctorId"
        val patientId = "patientId"
        val patientName = "Patient Name"
        val slotId = "slotId"
        val appointmentTimeEpoch = 1234567890L

        val doctor: DoctorDTO = mock {
            on { name } doReturn "Doctor Name"
        }
        whenever(doctorAvailabilityAPI.getDoctorInfo(doctorId)).thenReturn(doctor)
        val request = AppointmentConfirmationNotificationRequest(
            slotId = slotId,
            patientId = patientId,
            patientName = patientName,
            doctorId = doctorId,
            appointmentTimeEpoch = appointmentTimeEpoch,
        )
        val expectedNotification = AppointmentConfirmationNotification(
            patientName = patientName,
            doctorName = doctor.name,
            appointmentTimeEpoch = appointmentTimeEpoch,
        )

        val notificationAPI = DefaultNotificationAPI(
            notificationSender,
            doctorAvailabilityAPI,
        )

        notificationAPI.sendAppointmentNotification(request)

        verify(notificationSender, times(1)).send(expectedNotification)

    }

    @Test
    fun `sendAppointmentNotification should throw exception when doctor not found`() {
        val doctorId = "doctorId"
        val patientId = "patientId"
        val patientName = "Patient Name"
        val slotId = "slotId"
        val appointmentTimeEpoch = 1234567890L

        whenever(doctorAvailabilityAPI.getDoctorInfo(doctorId)).thenReturn(null)
        val request = AppointmentConfirmationNotificationRequest(
            slotId = slotId,
            patientId = patientId,
            patientName = patientName,
            doctorId = doctorId,
            appointmentTimeEpoch = appointmentTimeEpoch,
        )

        val notificationAPI = DefaultNotificationAPI(
            notificationSender,
            doctorAvailabilityAPI,
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            notificationAPI.sendAppointmentNotification(request)
        }.withMessage("Unknown doctor with id $doctorId")

    }

}
