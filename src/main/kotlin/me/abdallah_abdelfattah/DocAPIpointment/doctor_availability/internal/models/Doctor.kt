package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name

data class Doctor(
    val id: GUID = GUID(),
    val name: Name,
)


val DEFAULT_DOCTOR = Doctor(
    id = GUID(value="de99fd73-b66b-4853-8ca7-ce5766319f14"),
    name = Name("Ibrahim El-Masry")
)
