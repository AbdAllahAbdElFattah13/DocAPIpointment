package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.GUID
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GUIDTest{

    @Test
    fun `GUID should not be blank`(){
        assertThrows(IllegalArgumentException::class.java){
            GUID("")
        }
    }

    @Test
    fun `GUID should be a valid UUID`(){
        assertThrows(IllegalArgumentException::class.java){
            GUID("not a valid UUID")
        }
    }

    @Test
    fun `GUID gets auto-created`(){
        assertDoesNotThrow{
            GUID()
        }
    }

}
