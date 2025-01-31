package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.api.DoctorAvailabilityAPI
import org.springframework.stereotype.Component

@Component
class DefaultDoctorAvailabilityGateway(
    private val api: DoctorAvailabilityAPI,
) : DoctorAvailabilityGateway {

    override fun getAllDoctorsAvailableSlots() = api.getAllDoctorsAvailability()

    override fun getSlotById(slotId: String) = api.getSlotById(slotId)

    override fun reserveSlot(slotId: String) = api.reserveSlot(slotId)
}
