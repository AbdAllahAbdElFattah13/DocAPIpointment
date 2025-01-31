package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.controller.dto.ResponseSlot
import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Slot
import me.abdallah_abdelfattah.DocAPIpointment.shared.SharedModelsMapping
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(
    componentModel = "spring",
    uses = [SharedModelsMapping::class, CostMapper::class],
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    )
interface SlotMapper {
    @Mapping(target = "time", source = "time", qualifiedByName = ["mapTimeToEpoch"])
    fun toResponseSlot(slot: Slot): ResponseSlot

    fun toResponseSlots(slots: List<Slot>): List<ResponseSlot>
}
