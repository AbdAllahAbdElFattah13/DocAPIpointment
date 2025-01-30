package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID

data class UnknownDoctorException(val doctorId: String)
    : IllegalArgumentException("Doctor with id $doctorId is not found") {
    constructor(doctorId: GUID) : this(doctorId.value)
}

data class UnknownSlotException(val slotId: String)
    : IllegalArgumentException("Slot with id $slotId is not found") {
    constructor(slotId: GUID) : this(slotId.value)
}

sealed class UnavailableSlotException(
    msg: String,
) : IllegalArgumentException(msg) {

    data class SlotAlreadyReservedException(val slotId: String)
        : UnavailableSlotException("Slot with id $slotId is already reserved") {
        constructor(slotId: GUID) : this(slotId.value)
    }

    class SlotOverlapAnotherSlotException()
        : UnavailableSlotException("Another Slot overlaps with the new slot for the same doctor")

    data class SlotAlreadyExistException(private val slotDateTime: String)
        : UnavailableSlotException("Another Slot with the same time: $slotDateTime is already exist") {
        constructor(slotId: GUID) : this(slotId.value)
    }

}
