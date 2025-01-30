package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnknownDoctorException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.DEFAULT_DOCTOR
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.logger.ConsoleLogger
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InMemoryDoctorSlotRepositoryTest {


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

        val slot = Slot (
            doctorId = DEFAULT_DOCTOR.id,
            time = futureDate,
            cost = cost
        )

        repository.addDoctorSlot(slot)

        val doctorSlots = repository.getDoctorAvailability(DEFAULT_DOCTOR)

        assertEquals(listOf(slot), doctorSlots)
    }

}
