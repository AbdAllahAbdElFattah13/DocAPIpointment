package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.service

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.GetDoctorAvailabilityServiceResponse

interface DoctorAvailabilityService {

    fun getDoctorAvailability(doctorId: String): GetDoctorAvailabilityServiceResponse

    fun addDoctorSlot(
        doctorId: String,
        slotStartDateTimeValue: String,
        costValue: String,
    )
}
