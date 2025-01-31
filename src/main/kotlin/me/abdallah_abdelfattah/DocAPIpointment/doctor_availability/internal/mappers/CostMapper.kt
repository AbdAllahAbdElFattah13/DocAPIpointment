package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Cost
import org.springframework.stereotype.Component

@Component
class CostMapper {
    fun mapCost(cost: Cost): String = cost.value
}
