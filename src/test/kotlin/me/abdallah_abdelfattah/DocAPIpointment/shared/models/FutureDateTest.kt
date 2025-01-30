package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.Clock
import java.time.Instant

class FutureDateTest {

    @Test
    fun `Date must be in the future`() {
        assertThrows(IllegalArgumentException::class.java){
            "22/1/2020 12:00".toFutureDate()
        }
    }

    @Test
    fun `FutureDate will throw an exception if the date is not in the correct format`(){
        assertThrows(IllegalArgumentException::class.java) {
            "22-1-2023 12:00".toFutureDate()
        }
    }

    @Test
    fun `FutureDate will throw an exception if the date is not in the correct format 2`(){
        assertThrows(IllegalArgumentException::class.java) {
            "Invalid date format".toFutureDate()
        }
    }

    @Test
    fun `FutureDate works fine with future dates`() {

        val nowEpoch = 1674422400000 //"22/1/2023 12:00"
        val futureEpoch = 1738260746000 //"30/1/2025 18:12"

        val clock = mock<Clock> {
            on { instant() } doReturn Instant.ofEpochMilli(nowEpoch)
        }

        assertDoesNotThrow {
            FutureDate.fromEpochMillis(futureEpoch, clock)
        }
    }

}
