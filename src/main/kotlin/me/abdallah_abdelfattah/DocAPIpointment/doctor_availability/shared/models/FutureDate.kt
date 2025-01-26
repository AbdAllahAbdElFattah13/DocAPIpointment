package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

data class FutureDate(val dateTimeString: String) {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm")

    init {
        val dateTime: LocalDateTime = dateTimeString.toDateTimeWithFormat(formatter)
        require(isFuture(dateTime)) { "Date must be in the future: $dateTimeString" }
    }

    private fun isFuture(date: LocalDateTime): Boolean {
        return date.isAfter(LocalDateTime.now())
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
