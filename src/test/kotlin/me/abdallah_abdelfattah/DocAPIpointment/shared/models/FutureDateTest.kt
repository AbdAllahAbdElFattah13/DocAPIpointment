package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FutureDateTest {

    private val pattern = "d/M/yyyy H:mm"
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)

    private val now = LocalDateTime.parse(
        "21/1/2023 09:00",
        DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    )

    @Test
    fun `Date must be in the future`() {
        assertThrows(IllegalArgumentException::class.java){
            FutureDate("22/1/2020 12:00")
        }
    }

    @Test
    fun `FutureDate will throw an exception if the date is not in the correct format`(){
        assertThrows(IllegalArgumentException::class.java) {
            FutureDate("22-1-2023 12:00")
        }
    }

    @Test
    fun `FutureDate will throw an exception if the date is not in the correct format 2`(){
        assertThrows(IllegalArgumentException::class.java) {
            FutureDate("Invalid date format")
        }
    }

    @Test
    fun `FutureDate works fine with future dates`() {
        val dateTimeString = "22/1/2023 12:00"
        assertDoesNotThrow{
            val futureDate = FutureDate(dateTimeString, now)
            assertEquals(dateTimeString, futureDate.dateTimeString)
            assertEquals(
                LocalDateTime.parse(dateTimeString, formatter),
                futureDate.dateTime,
            )

        }
    }

}
