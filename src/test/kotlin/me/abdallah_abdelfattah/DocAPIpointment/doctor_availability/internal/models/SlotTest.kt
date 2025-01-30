package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.futureDateEpoch
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.nowEpoch
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant
import kotlin.test.Test

class SlotTest {

    private val clock: Clock = mock {
        on(it.instant()) doReturn (Instant.ofEpochMilli(nowEpoch))
    }
    private val time = FutureDate.fromEpochMillis(
        futureDateEpoch,
        clock,
    )

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

        val endDateTime = slot.endEpochMillis

        assertEquals(
            time.epochMillis + 60 * 60 * 1000,
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
