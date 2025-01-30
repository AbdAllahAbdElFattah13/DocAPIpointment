package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.test.Test

class SlotTest {

    private val dateTimeString = "22/1/2023 12:00"
    private val now = LocalDateTime.parse(
        "21/1/2023 09:00",
        DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    )
    private val time = FutureDate(dateTimeString, now)

    @Test
    fun `reserve should return a new Slot with isReserved set to true`() {
        val slot = Slot(
            doctorId = GUID(),
            time = time,
            isReserved = false,
            cost = Cost.fromNumber(100.0)
        )

        val reservedSlot = slot.reserve()

        assertTrue(reservedSlot.isReserved)
    }

    @Test
    fun `endDateTime should return the time plus the duration`() {
        val slot = Slot(
            doctorId = GUID(),
            time = time,
            isReserved = false,
            cost = Cost.fromNumber(100.0),
            durationInMinutes = 60
        )

        val endDateTime = slot.endDateTime

        assertEquals(
            LocalDateTime.parse(
                "22/1/2023 13:00",
                DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
            ),
            endDateTime
        )
    }

    @Test
    fun `Slot should throw an exception if the duration is less than 15 minutes`() {
        assertThrows(IllegalArgumentException::class.java) {
            Slot(
                doctorId = GUID(),
                time = time,
                isReserved = false,
                cost = Cost.fromNumber(100.0),
                durationInMinutes = 10
            )
        }
    }

    @Test
    fun `Slot should not throw an exception if the duration is 15 minutes or more`() {
        assertDoesNotThrow {
            Slot(
                doctorId = GUID(),
                time = time,
                isReserved = false,
                cost = Cost.fromNumber(100.0),
                durationInMinutes = 15
            )
        }
    }
}
