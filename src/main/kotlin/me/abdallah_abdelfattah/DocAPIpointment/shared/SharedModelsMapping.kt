package me.abdallah_abdelfattah.DocAPIpointment.shared

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.FutureDate
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.mapstruct.Named
import org.springframework.stereotype.Component

@Component
class SharedModelsMapping {
    fun mapId(id: GUID): String = id.value

    @Named("mapTimeToEpoch")
    fun mapTimeToEpoch(time: FutureDate): String = time.epochMillis.toString()

    @Named("mapTimeToString")
    fun mapTimeToString(time: FutureDate): String = time.format()

    fun mapName(name: Name): String = name.value
}
