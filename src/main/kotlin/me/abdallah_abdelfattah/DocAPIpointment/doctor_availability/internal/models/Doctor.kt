package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.models.Name

data class Doctor(
    val id: GUID = GUID(),
    val name: Name,
)
