package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

data class Cost(private val rawValue: String) {
    val value: String

    init {
        value = validateAndFormatValue(rawValue)
    }

    private fun validateAndFormatValue(input: String): String {
        try {
            val number = input.toDoubleOrNull()
                ?: throw IllegalArgumentException("Cost: Invalid number format: $input")

            require(number > 0) {"Cost: cannot accept negative value: $input"}

            return "%.2f".format(number)
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Cost: Invalid number format: $input")
        }
    }

    companion object {
        fun fromNumber(number: Number): Cost {
            return Cost(number.toString())
        }
    }
}
