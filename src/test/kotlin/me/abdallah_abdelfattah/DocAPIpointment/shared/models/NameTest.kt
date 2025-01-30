package me.abdallah_abdelfattah.DocAPIpointment.shared.models

import me.abdallah_abdelfattah.DocAPIpointment.shared.models.Name
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class NameTest {

    @Test
    fun `Name cannot be blank`() {
        assertThrows(IllegalArgumentException::class.java) {
            Name("")
        }
    }

    @Test
    fun `Name cannot be shorter than 2 characters`() {
        assertThrows(IllegalArgumentException::class.java) {
            Name("a")
        }
    }

    @Test
    fun `Name cannot be longer than 50 characters`() {
        assertThrows(IllegalArgumentException::class.java) {
            Name("a".repeat(51))
        }
    }

    @Test
    fun `Name can be 2 characters`() {
        val name = Name("ab")
        assert(name.value == "ab")
    }

    @Test
    fun `Normal name`() {
        val name = Name("Mohamed")
        assert(name.value == "Mohamed")
    }

}
