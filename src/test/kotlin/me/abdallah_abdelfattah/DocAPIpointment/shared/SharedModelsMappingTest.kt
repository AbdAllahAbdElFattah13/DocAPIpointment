package me.abdallah_abdelfattah.DocAPIpointment.shared

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Clock


class SharedModelsMappingTest {

    private val sharedModelsMapping = SharedModelsMapping()
    private val clock = Clock.systemUTC()
    private val time = FutureDate.fromEpochMillis(
        clock.millis().plus(mintToMillis(120))
    )

    @Test
    fun testMapId() {
        val id = GUID()
        val result = sharedModelsMapping.mapId(id)
        assertThat(result).isEqualTo(id.value)
    }

    @Test
    fun testMapTimeToEpoch() {
        val result = sharedModelsMapping.mapTimeToEpoch(time)
        assertThat(result).isEqualTo(time.epochMillis.toString())
    }

    @Test
    fun testMapTimeToString() {
        val result = sharedModelsMapping.mapTimeToString(time)
        assertThat(result).isEqualTo(time.format())
    }

    @Test
    fun testMapName() {
        val name = Name("John Doe")
        val result = sharedModelsMapping.mapName(name)
        assertThat(result).isEqualTo(name.value)
    }
}
