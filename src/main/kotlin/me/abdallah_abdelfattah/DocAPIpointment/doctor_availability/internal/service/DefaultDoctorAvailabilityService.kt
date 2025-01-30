package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.service

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.UnavailableSlotException
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.GetDoctorAvailabilityServiceResponse
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers.SlotMapper
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Cost
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Doctor
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.repository.DoctorSlotRepository
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.springframework.stereotype.Service

@Service
class DefaultDoctorAvailabilityService(
    private val doctorSlotRepository: DoctorSlotRepository,
    private val slotMapper: SlotMapper,
) : DoctorAvailabilityService {

    override fun getDoctorAvailability(doctorId: String): GetDoctorAvailabilityServiceResponse {

        val doctor = getDoctor(doctorId)
        val doctorSlots = doctorSlotRepository.getDoctorAvailability(doctor)

        return GetDoctorAvailabilityServiceResponse(
            doctorId = doctor.id.value,
            slots = slotMapper.toResponseSlots(doctorSlots)
        )
    }

    override fun addDoctorSlot(
        doctorId: String,
        slotStartEpochTime: String,
        costValue: String,
        ) {
        val doctor = getDoctor(doctorId)
        val time = FutureDate.fromEpochMillis(slotStartEpochTime)
        val cost = Cost(costValue)

        val slot = Slot(
            doctorId = doctor.id,
            time = time,
            cost = cost
        )

        val doctorAvailability = doctorSlotRepository.getDoctorAvailability(doctor)

        assertSlotIsAvailable(doctorAvailability, slot)
        assertNoOverlapInSlots(doctorAvailability, slot)

        doctorSlotRepository.addDoctorSlot(slot)
    }

    fun assertNoOverlapInSlots(
        doctorAvailability: List<Slot>,
        newSlot: Slot,
    ) {

        val newSlotStartTime = newSlot.time.epochMillis
        val newSlotEndTime = newSlot.endEpochMillis

        if (doctorAvailability.any {
                val existingSlotStartTime = it.time.epochMillis
                val existingSlotEndTime = it.endEpochMillis

                newSlotStartTime < existingSlotEndTime
                        && existingSlotStartTime < newSlotEndTime
            }) {
            throw UnavailableSlotException.SlotOverlapAnotherSlotException()
        }
    }

    private fun getDoctor(doctorId: String): Doctor {
        return doctorSlotRepository.getDoctor(GUID(doctorId))
    }

    private fun assertSlotIsAvailable(
        doctorAvailability: List<Slot>,
        slot: Slot,
        ) {
        if (doctorAvailability.any { it.time.epochMillis == slot.time.epochMillis }) {
            throw UnavailableSlotException.SlotAlreadyExistException(
                slot.time.format()
            )
        }
    }
}
