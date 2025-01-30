package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class FutureDate(
    val dateTimeString: String,
    private val now: LocalDateTime = LocalDateTime.now(),
) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm")
    val dateTime: LocalDateTime = dateTimeString.toDateTimeWithFormat(formatter)

    init {
        require(dateTime.isAfter(now)) { "Date must be in the future: $dateTimeString" }
    }
}

fun String.toDateTimeWithFormat(formatter: DateTimeFormatter): LocalDateTime {
    return try {
        LocalDateTime.parse(this, formatter)
    } catch (e: DateTimeParseException) {
        throw IllegalArgumentException("Invalid date format. Expected '$formatter'")
    }
}

fun String.toFutureDates() = FutureDate(this)
