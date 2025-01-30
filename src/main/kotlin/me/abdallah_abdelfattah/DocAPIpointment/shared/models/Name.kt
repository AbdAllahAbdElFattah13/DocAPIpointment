package me.abdallah_abdelfattah.DocAPIpointment.shared.models

data class Name(val value: String) {
    private val minLength = 2
    private val maxLength = 50

    init {
        require(value.isNotBlank()) { "Name cannot be blank" }
        require(value.length >= minLength) { "Name cannot be shorter than 2 characters" }
        require(value.length <= maxLength) { "Name cannot be longer than $maxLength characters" }
    }
}
