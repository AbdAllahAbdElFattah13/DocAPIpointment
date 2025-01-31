package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.mappers.DoctorAvailabilityModelsMapper
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Slot
import org.springframework.stereotype.Service

@Service
class ShowAvailableSlotsUseCase(
    private val doctorAvailabilityGateway: DoctorAvailabilityGateway,
    private val doctorAvailabilityMapper: DoctorAvailabilityModelsMapper,
) {
    fun run(): List<Slot> = doctorAvailabilityGateway
        .getAllDoctorsAvailableSlots()
        .map(doctorAvailabilityMapper::mapToSlot)
}
