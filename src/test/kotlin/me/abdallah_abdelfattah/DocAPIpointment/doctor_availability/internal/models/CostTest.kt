package me.abdallah_abdelfattah.DocAPIpointment.doctor_availability.internal.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CostTest {

    @Test
    fun `Cost should not be negative`(){
        assertThrows(IllegalArgumentException::class.java){
            Cost.fromNumber(-1.0)
        }
    }

    @Test
    fun `Cost should not be negative even if it is a whole number`(){
        assertThrows(IllegalArgumentException::class.java){
            Cost.fromNumber(-1)
        }
    }

    @Test
    fun `Cost should only be numbers`(){
        assertThrows(IllegalArgumentException::class.java){
            Cost("not a number")
        }
    }

    @Test
    fun `Cost should be formatted to 2 decimal places`(){
        val cost = Cost.fromNumber(100.123)
        assertEquals("100.12", cost.value)
    }

    @Test
    fun `Cost should be rounded to 2 decimal places`(){
        val cost = Cost.fromNumber(100.126)
        assertEquals("100.13", cost.value)
    }
}
