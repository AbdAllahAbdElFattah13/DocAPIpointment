package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name

data class Patient(
    val id: GUID = GUID(),
    val name: Name,
)

val DEFAULT_Patient = Patient(
    id = GUID(value = "6c26cc8b-6f02-4e06-8c02-a070388934c5"),
    name = Name("Mohamed El-Shazly"),
)
