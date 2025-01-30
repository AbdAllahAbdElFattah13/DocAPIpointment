package me.abdallah_abdelfattah.DocAPIpointment.shared

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Cost
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.time_provider.TimeProvider
import java.time.LocalDateTime
import java.time.ZoneOffset

val now = LocalDateTime.parse("2010-12-03T10:15:30")

val futureDate = FutureDate(
    dateTimeString = "25/1/2011 20:00",
    now = now,
)

val cost = Cost(rawValue = "100.00")

class TimeProviderStud : TimeProvider {
    override fun now(): Long {
        return now.toEpochSecond(ZoneOffset.UTC)
    }
}
