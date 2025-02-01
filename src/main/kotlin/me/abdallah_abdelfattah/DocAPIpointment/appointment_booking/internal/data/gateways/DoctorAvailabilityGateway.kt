package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.gateways.mappers.DoctorAvailabilityModelsMapper
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DoctorAvailabilityAPI
import org.springframework.stereotype.Component

@Component
class DefaultDoctorAvailabilityGateway(
    private val api: DoctorAvailabilityAPI,
    private val mapper: DoctorAvailabilityModelsMapper,
) : DoctorAvailabilityGateway {

    override fun getAllDoctorsAvailableSlots() = api.getAllDoctorsAvailability().map(mapper::mapToSlot)

    override fun getSlotById(slotId: String) = api.getSlotById(slotId)?.let(mapper::mapToSlot)

    override fun reserveSlot(slotId: String) = api.reserveSlot(slotId)
}
