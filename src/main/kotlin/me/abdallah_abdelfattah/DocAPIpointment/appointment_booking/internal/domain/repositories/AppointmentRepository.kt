package me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.repositories

import me.abdallah_abdelfattah.DocAPIpointment.appointment_booking.internal.domain.models.Appointment

interface AppointmentRepository {
    fun save(appointment: Appointment)
    fun findById(id: String): Appointment?
    fun update(id: String, newAppointment: Appointment)
}
