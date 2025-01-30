package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.service

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnavailableSlotException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.GetDoctorAvailabilityServiceResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers.SlotMapper
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.DEFAULT_DOCTOR
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository.DoctorSlotRepository
import me.abdallah_abdelfattah.DocAPIpointment.shared.*
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.*
import java.time.Clock
import kotlin.test.Test

class DefaultDoctorAvailabilityServiceTest {

    private val now = Clock.systemUTC().instant().toEpochMilli()

    private val futureDate = FutureDate.fromEpochMillis(
        now.plus(mintToMillis(24 * 60))
    )
    private val responseSlot: ResponseSlot = mock()
    private val slot = Slot(
        doctorId = DEFAULT_DOCTOR.id,
        time = futureDate,
        cost = cost,
    )

    private val repo: DoctorSlotRepository = mock()
    private val slotMapper: SlotMapper = mock {
        on(it.toResponseSlot(slot))
            .thenReturn(responseSlot)
        on(it.toResponseSlots(listOf(slot)))
            .thenReturn(listOf(responseSlot))
    }

    private val service = DefaultDoctorAvailabilityService(
        repo,
        slotMapper,
    )

    @BeforeEach
    fun setup() {
        reset(repo)
        whenever(repo.getDoctor(DEFAULT_DOCTOR.id))
            .thenReturn(DEFAULT_DOCTOR)
    }

    @Test
    fun `getDoctorAvailability should return doctor availability`() {

        whenever(repo.getDoctorAvailability(DEFAULT_DOCTOR))
            .thenReturn(listOf(slot))

        val expected = GetDoctorAvailabilityServiceResponse(
            doctorId = DEFAULT_DOCTOR.id.value,
            slots = listOf(responseSlot)
        )

        val availability = service.getDoctorAvailability(DEFAULT_DOCTOR.id.value)
        assertThat(availability).isEqualTo(expected)
        verify(repo, times(1)).getDoctorAvailability(DEFAULT_DOCTOR)
    }

    @Test
    fun `addDoctorSlot when called with valid slot should call the repo with the correct params`() {

        val slotCaptor = argumentCaptor<Slot>()

        service.addDoctorSlot(
            doctorId = DEFAULT_DOCTOR.id.value,
            slotStartEpochTime = futureDate.epochMillis.toString(),
            costValue = cost.value,
        )

        verify(repo, times(1)).addDoctorSlot(slotCaptor.capture())
        val capturedSlot = slotCaptor.firstValue
        assertThat(capturedSlot.doctorId).isEqualTo(DEFAULT_DOCTOR.id)
        assertThat(capturedSlot.time.epochMillis).isEqualTo(futureDate.epochMillis)
        assertThat(capturedSlot.cost.value).isEqualTo(cost.value)
    }


    @Test
    fun `addDoctorSlot should throw SlotAlreadyExistException if the slot is already there with the same start time for the same doctor`() {
        whenever(repo.getDoctorAvailability(DEFAULT_DOCTOR))
            .thenReturn(listOf(slot))

        assertThatExceptionOfType(UnavailableSlotException.SlotAlreadyExistException::class.java).isThrownBy {
            service.addDoctorSlot(
                doctorId = DEFAULT_DOCTOR.id.value,
                slotStartEpochTime = futureDate.epochMillis.toString(),
                costValue = cost.value,
            )
        }.withMessage("Another Slot with the same time: ${futureDate.format()} is already exist")

    }

    @Test
    fun `assertNoOverlapInSlots should throw SlotOverlapAnotherSlotException when overlapping occurred 1`() {
        val futureDatePlus10Mints = FutureDate.fromEpochMillis(
            futureDate.epochMillis.plus(mintToMillis(10))
        )
        val doctorAvailability = listOf(slot)
        val newSlot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = futureDatePlus10Mints,
            cost = cost
        )

        assertThatExceptionOfType(UnavailableSlotException.SlotOverlapAnotherSlotException::class.java).isThrownBy {
            service.assertNoOverlapInSlots(
                doctorAvailability,
                newSlot,
            )
        }.withMessage("Another Slot overlaps with the new slot for the same doctor")
    }

    @Test
    fun `assertNoOverlapInSlots should throw SlotOverlapAnotherSlotException when overlapping occurred 2`() {
        val futureDateMinus30Mints = FutureDate.fromEpochMillis(
            futureDate.epochMillis.minus(mintToMillis(30))
        )
        val doctorAvailability = listOf(slot)
        val newSlot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = futureDateMinus30Mints,
            cost = cost
        )

        assertThatExceptionOfType(UnavailableSlotException.SlotOverlapAnotherSlotException::class.java).isThrownBy {
            service.assertNoOverlapInSlots(
                doctorAvailability,
                newSlot,
            )
        }.withMessage("Another Slot overlaps with the new slot for the same doctor")
    }

    @Test
    fun `assertNoOverlapInSlots should throw SlotOverlapAnotherSlotException when overlapping occurred 3`() {
        val futureDatePlus10Mints = FutureDate.fromEpochMillis(
            futureDate.epochMillis.minus(mintToMillis(10))
        )
        val doctorAvailability = listOf(slot)
        val newSlot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = futureDatePlus10Mints,
            durationInMinutes = 60,
            cost = cost
        )

        assertThatExceptionOfType(UnavailableSlotException.SlotOverlapAnotherSlotException::class.java).isThrownBy {
            service.assertNoOverlapInSlots(
                doctorAvailability,
                newSlot,
            )
        }.withMessage("Another Slot overlaps with the new slot for the same doctor")
    }

    @Test
    fun `assertNoOverlapInSlots should not throw for none overlapping slots`() {
        val futureDatePlus1Day =FutureDate.fromEpochMillis(
            futureDate.epochMillis.plus(mintToMillis(24 * 60))
        )
        val doctorAvailability = listOf(slot)
        val newSlot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = futureDatePlus1Day,
            cost = cost
        )

        service.assertNoOverlapInSlots(doctorAvailability, newSlot)
    }

}
