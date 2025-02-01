package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownDoctorException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownSlotException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.DEFAULT_DOCTOR
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Doctor
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDateEpoch
import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.ConsoleLogger
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import me.abdallah_abdelfattah.DocAPIpointment.shared.nowEpoch
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant

class InMemoryDoctorSlotRepositoryTest {

    private val clock: Clock = mock {
        on(it.instant()) doReturn (Instant.ofEpochMilli(nowEpoch))
    }

    @Test
    fun `assert default doctor gets added`() {
        val repository = InMemoryDoctorSlotRepository(ConsoleLogger())

        val doctor = repository.getDoctor(DEFAULT_DOCTOR.id)

        assertEquals(DEFAULT_DOCTOR, doctor)
    }

    @Test
    fun `assert doctor not found`() {
        val repository = InMemoryDoctorSlotRepository(ConsoleLogger())

        assertThrows(UnknownDoctorException::class.java) {
            repository.getDoctor(GUID())
        }
    }

    @Test
    fun `assert doctor slot gets added`() {
        val repository = InMemoryDoctorSlotRepository(ConsoleLogger())

        val slot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )

        repository.addDoctorSlot(slot)

        val doctorSlots = repository.getDoctorAvailability(DEFAULT_DOCTOR)

        assertEquals(listOf(slot), doctorSlots)
    }

    @Test
    fun `assert all doctors availability`() {
        val repository = InMemoryDoctorSlotRepository(mock())
        val doctor2 = Doctor(
            id = GUID(),
            name = Name("Doctor 2")
        )

        val availableSlot1 = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )
        val availableSlot2 = Slot(
            doctorId = doctor2.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )
        val reservedSlot1 = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost,
            reserved = true
        )
        val reservedSlot2 = Slot(
            doctorId = doctor2.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost,
            reserved = true
        )
        val expected = listOf(availableSlot1, availableSlot2)

        repository.addDoctorSlot(availableSlot1)
        repository.addDoctorSlot(availableSlot2)
        repository.addDoctorSlot(reservedSlot1)
        repository.addDoctorSlot(reservedSlot2)

        val allAvailableSlots = repository.getAllDoctorsAvailability()

        assertEquals(expected, allAvailableSlots)
    }

    @Test
    fun `assert slot by id - found`() {
        val repository = InMemoryDoctorSlotRepository(mock())
        val slot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )

        repository.addDoctorSlot(slot)

        val foundSlot = repository.getSlotById(slot.id.value)

        assertEquals(slot, foundSlot)
    }

    @Test
    fun `assert slot by id - not found`() {
        val repository = InMemoryDoctorSlotRepository(mock())

        val foundSlot = repository.getSlotById(GUID().value)

        assertNull(foundSlot)
    }

    @Test
    fun `assert slot gets updated`() {
        val repository = InMemoryDoctorSlotRepository(mock())
        val slot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )

        repository.addDoctorSlot(slot)

        val updatedSlot = slot.copy(reserved = true)

        repository.updateSlot(slot.id.value, updatedSlot)

        val foundSlot = repository.getSlotById(slot.id.value)

        assertEquals(updatedSlot, foundSlot)
    }

    @Test
    fun `assert slot not found for update`() {
        val repository = InMemoryDoctorSlotRepository(mock())
        val slot = Slot(
            doctorId = DEFAULT_DOCTOR.id,
            time = FutureDate.fromEpochMillis(futureDateEpoch, clock),
            cost = cost
        )

        val updatedSlot = slot.copy(reserved = true)

        assertThrows(UnknownSlotException::class.java) {
            repository.updateSlot(slot.id.value, updatedSlot)
        }
    }

    @Test
    fun `assert doctor by id`() {
        val repository = InMemoryDoctorSlotRepository(mock())

        val doctor = repository.getDoctorById(DEFAULT_DOCTOR.id.value)

        assertEquals(DEFAULT_DOCTOR, doctor)
    }

    @Test
    fun `assert doctor by id - not found`() {
        val repository = InMemoryDoctorSlotRepository(mock())

        val doctor = repository.getDoctorById(GUID().value)

        assertNull(doctor)
    }

}
