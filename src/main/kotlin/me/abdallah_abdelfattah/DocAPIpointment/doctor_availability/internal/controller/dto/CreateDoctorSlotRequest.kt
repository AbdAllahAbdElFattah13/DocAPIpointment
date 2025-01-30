package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto

data class CreateDoctorSlotRequest(
    val slotStartDateTime: String,
    val cost: String,
)
