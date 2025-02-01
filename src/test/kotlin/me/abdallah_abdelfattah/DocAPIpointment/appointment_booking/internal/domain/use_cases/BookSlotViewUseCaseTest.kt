package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.AppointmentStatus
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Patient
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.PatientRepository
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.SlotDTO
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*

class BookSlotViewUseCaseTest {

    private val doctorAvailabilityGateway: DoctorAvailabilityGateway = mock()
    private val patientRepository: PatientRepository = mock()
    private val appointmentRepository: AppointmentRepository = mock()

    private val mockGUID = "6c80f42d-ab54-44b6-a69b-3b5e2a3107e1"

    @BeforeEach
    fun setUp() {
        reset(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )
    }

    @Test
    fun `BookSlotUseCase throws exception when patient not found`() {

        val patientId = "patientId"
        val slotId = "slotId"

        whenever(patientRepository.findById(patientId)).thenReturn(null)

        val bookSlotUseCase = BookSlotUseCase(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            bookSlotUseCase.run(patientId, slotId)
        }.withMessage("Patient with id $patientId not found, please create it first")

    }

    @Test
    fun `BookSlotUseCase throws exception when slot not found`() {

        val patientId = "patientId"
        val slotId = "slotId"

        whenever(patientRepository.findById(patientId)).thenReturn(mock())
        whenever(doctorAvailabilityGateway.getSlotById(slotId)).thenReturn(null)

        val bookSlotUseCase = BookSlotUseCase(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            bookSlotUseCase.run(patientId, slotId)
        }.withMessage("Slot with id $slotId not found")

    }

    @Test
    fun `BookSlotUseCase throws exception when slot is already reserved`() {

        val patientId = "patientId"
        val slotId = "slotId"
        val doctorId = "doctorId"

        val slotDTO = SlotDTO(
            id = slotId,
            doctorId = doctorId,
            startTimeEpoch = 0,
            endTimeEpoch = 0,
            reserved = true,
            cost = "0",
        )

        whenever(patientRepository.findById(patientId)).thenReturn(mock())
        whenever(doctorAvailabilityGateway.getSlotById(slotId)).thenReturn(slotDTO)

        val bookSlotUseCase = BookSlotUseCase(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            bookSlotUseCase.run(patientId, slotId)
        }.withMessage("Slot with id $slotId is already reserved")
    }

    @Test
    fun `BookSlotUseCase throws exception when slot reservation fails`() {

        val patientId = "patientId"
        val slotId = "slotId"
        val doctorId = "doctorId"

        val slotDTO = SlotDTO(
            id = slotId,
            doctorId = doctorId,
            startTimeEpoch = 0,
            endTimeEpoch = 0,
            reserved = false,
            cost = "0",
        )

        whenever(patientRepository.findById(patientId)).thenReturn(mock())
        whenever(doctorAvailabilityGateway.getSlotById(slotId)).thenReturn(slotDTO)
        whenever(doctorAvailabilityGateway.reserveSlot(slotId)).thenReturn(false)

        val bookSlotUseCase = BookSlotUseCase(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            bookSlotUseCase.run(patientId, slotId)
        }.withMessage("Failed to reserve slot with id $slotId for patient $patientId")
    }

    @Test
    fun `BookSlotUseCase saves appointment to appointment repo when slot is successfully booked`() {

        val patientId = mockGUID
        val slotId = mockGUID
        val doctorId = mockGUID
        val appointmentCaptor = argumentCaptor<Appointment>()

        val patient = Patient(
            id = GUID(patientId),
            name = Name("Patient Name"),
        )
        val slotDTO = SlotDTO(
            id = slotId,
            doctorId = doctorId,
            startTimeEpoch = 0,
            endTimeEpoch = 0,
            reserved = false,
            cost = "0",
        )

        whenever(patientRepository.findById(patientId)).thenReturn(patient)
        whenever(doctorAvailabilityGateway.getSlotById(slotId)).thenReturn(slotDTO)
        whenever(doctorAvailabilityGateway.reserveSlot(slotId)).thenReturn(true)

        val bookSlotUseCase = BookSlotUseCase(
            doctorAvailabilityGateway,
            patientRepository,
            appointmentRepository,
        )

        bookSlotUseCase.run(patientId, slotId)

        verify(appointmentRepository, times(1)).save(appointmentCaptor.capture())
        val capturedAppointment = appointmentCaptor.firstValue
        assertThat(capturedAppointment.id).isNotNull
        assertThat(capturedAppointment.patient).isEqualTo(patient)
        assertThat(capturedAppointment.doctorId).isEqualTo(GUID(slotDTO.doctorId))
        assertThat(capturedAppointment.slotId).isEqualTo(GUID(slotDTO.id))
        assertThat(capturedAppointment.reservedAtEpoch).isNotNull
        assertThat(capturedAppointment.createdAtEpoch).isNotNull
        assertThat(capturedAppointment.status).isEqualTo(AppointmentStatus.PENDING)
        assertThat(capturedAppointment.lastUpdatedAtEpoch).isNull()
    }

}
