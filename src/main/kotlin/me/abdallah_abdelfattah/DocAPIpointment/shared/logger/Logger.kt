package me.abdallah_abdelfattah.DocAPIpointment.shared.logger

interface Logger {
    fun info(message: String?)

    fun info(message: String?, vararg arguments: Any?)

    fun debug(message: String?)

    fun debug(message: String?, vararg arguments: Any?)

    fun warn(message: String?)

    fun warn(message: String?, vararg arguments: Any?)

    fun error(message: String?)

    fun error(message: String?, vararg arguments: Any?)
}
