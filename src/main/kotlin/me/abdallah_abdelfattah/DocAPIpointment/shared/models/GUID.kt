package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import java.util.UUID

data class GUID(val value: String = UUID.randomUUID().toString()) {

    init {
        require(value.isNotBlank()) { "GUID cannot be blank" }
        require(value.isValidUUID()) { "Invalid UUID: $value" }
    }

    private fun String.isValidUUID(): Boolean {
        return try {
            UUID.fromString(this)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}
