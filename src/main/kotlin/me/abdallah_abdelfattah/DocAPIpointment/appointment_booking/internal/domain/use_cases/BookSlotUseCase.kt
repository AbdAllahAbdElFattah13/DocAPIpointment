package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.use_cases

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.DoctorAvailabilityGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.gateways.NotificationGateway
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.PatientRepository
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BookSlotUseCase(
    private val doctorAvailabilityGateway: DoctorAvailabilityGateway,
    private val patientRepository: PatientRepository,
    private val appointmentRepository: AppointmentRepository,
    private val notificationGateway: NotificationGateway,
) {
    fun run(
        patientId: String,
        slotId: String,
    ) {
        val patient = patientRepository.findById(patientId)
            ?: throw IllegalArgumentException("Patient with id $patientId not found, please create it first")

        val slot = doctorAvailabilityGateway.getSlotById(slotId)
            ?: throw IllegalArgumentException("Slot with id $slotId not found")

        require(!slot.reserved) { "Slot with id $slotId is already reserved" }

        val result = doctorAvailabilityGateway.reserveSlot(slotId)
        require(result) { "Failed to reserve slot with id $slotId for patient $patientId" }

        appointmentRepository.save(
            Appointment(
                id = GUID(),
                patient = patient,
                doctorId = GUID(slot.doctorId),
                slotId = GUID(slot.id),
                reservedAtEpoch = Instant.now().toEpochMilli(),
            )
        )

        notificationGateway.notifyNewAppointment(
            slotId = slotId,
            patientId = patientId,
            patientName = patient.name.value,
            doctorId = slot.doctorId,
            appointmentTimeEpoch = slot.startTimeEpoch,
        )

    }
}
