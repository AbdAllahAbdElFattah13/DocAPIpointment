package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Doctor
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.shared.dtos.DoctorDTO
import me.abdallah_abdelfattah.DocAPIpointment.shared.SharedModelsMapping
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper

@Mapper(
    componentModel = "spring",
    uses = [SharedModelsMapping::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
)
interface DoctorMapper {
    fun toDoctorDTO(doctor: Doctor): DoctorDTO
}
