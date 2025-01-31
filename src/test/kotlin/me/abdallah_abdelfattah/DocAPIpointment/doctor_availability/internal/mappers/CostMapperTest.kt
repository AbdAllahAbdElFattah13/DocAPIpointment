package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.mappers

import me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models.Cost
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class CostMapperTest {
    @Test
    fun `mapCost should return the value of the cost`() {
        val cost = Cost.fromNumber(10.0)
        val costMapper = CostMapper()

        assertThat(costMapper.mapCost(cost))
            .isEqualTo("10.00")
    }
}
