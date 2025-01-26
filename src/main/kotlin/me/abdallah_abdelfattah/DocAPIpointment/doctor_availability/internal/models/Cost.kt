package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

data class Cost(private val rawValue: String) {
    val value: String

    init {
        value = validateAndFormatValue(rawValue)
    }

    private fun validateAndFormatValue(input: String): String {
        try {
            val number = input.toDoubleOrNull()
                ?: throw IllegalArgumentException("Invalid number format: $input")

            require(number < 0) {"Cost cannot be negative: $input"}

            if (number % 1.0 == 0.0) {
                return number.toLong().toString()
            }

            return "%.2f".format(number)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid number format: $input")
        }
    }

    companion object {
        fun fromNumber(number: Number): Cost {
            return Cost(number.toString())
        }
    }
}
