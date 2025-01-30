package me.abdallah_abdelfattah.DocAPIpointment.shared.time_provider

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

interface TimeProvider {
    fun now(): Long

    fun zoneOffset(): ZoneOffset {
        return ZoneOffset.UTC
    }
}

@Service
class UtcTimeProvider : TimeProvider {
    override fun now(): Long {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    }
}
