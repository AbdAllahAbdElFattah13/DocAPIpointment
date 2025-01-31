package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class FutureDate private constructor(
    private val instant: Instant
) {
    companion object {
        private val UTC_ZONE = ZoneId.of("UTC")

        fun fromInstant(instant: Instant, clock: Clock = Clock.systemUTC()): FutureDate {

            val now = clock.instant()

            require(instant.isAfter(now)) {
                "FutureDate: Date must be in the future: $instant. time now $now"
            }
            return FutureDate(instant)
        }

        fun fromEpochMillis(epochMillis: Long, clock: Clock = Clock.systemUTC()): FutureDate {
            return fromInstant(Instant.ofEpochMilli(epochMillis), clock)
        }

        fun fromEpochMillis(epochMillis: String, clock: Clock = Clock.systemUTC()): FutureDate {
            val parsedEpochMillis = try {
                epochMillis.toLong()
            } catch (e: NumberFormatException) {
                throw IllegalArgumentException("FutureDate: Invalid epochMillis: $epochMillis")
            }

            return fromInstant(Instant.ofEpochMilli(parsedEpochMillis), clock)
        }

        fun now(clock: Clock = Clock.systemUTC()): FutureDate {
            return fromInstant(clock.instant(), clock)
        }

        fun parse(dateStr: String, pattern: String = "d/M/yyyy H:mm"): FutureDate {
            val formatter = DateTimeFormatter.ofPattern(pattern)
                .withZone(UTC_ZONE)
            val instant = formatter.parse(dateStr, Instant::from)
            return fromInstant(instant)
        }
    }

    val epochMillis: Long = instant.toEpochMilli()

    fun format(pattern: String = "d/M/yyyy H:mm", zone: ZoneId = UTC_ZONE): String {
        val formatter = DateTimeFormatter.ofPattern(pattern)
            .withZone(zone)
        return formatter.format(instant)
    }

    override fun toString(): String = format()
}

fun String.toFutureDate(pattern: String = "d/M/yyyy H:mm"): FutureDate {

    val formatter = DateTimeFormatter.ofPattern(pattern)
        .withZone(ZoneId.of("UTC"))

    val epochMillis = try {
        formatter.parse(this, Instant::from)
            .toEpochMilli()
    } catch (e: DateTimeParseException) {
        throw IllegalArgumentException("FutureDate: Invalid date format: $this")
    }

    return FutureDate.fromEpochMillis(epochMillis)
}
