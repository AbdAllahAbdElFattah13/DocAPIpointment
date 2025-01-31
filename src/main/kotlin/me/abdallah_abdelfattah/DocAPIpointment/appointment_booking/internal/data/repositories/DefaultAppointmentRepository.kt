package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.data.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment
import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories.AppointmentRepository
import org.springframework.stereotype.Repository

@Repository
class DefaultAppointmentRepository : AppointmentRepository {
    private val appointments = mutableMapOf<String, Appointment>()

    override fun save(appointment: Appointment) {
        appointments[appointment.id.value] = appointment
    }

    override fun findById(id: String): Appointment? {
        return appointments[id]
    }

    override fun update(id: String, newAppointment: Appointment) {
        appointments[id] = newAppointment
    }
}
