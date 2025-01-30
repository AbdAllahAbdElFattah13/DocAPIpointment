package me.abdallah_abdelfattah.DocAPIpointment.shared.logger

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ConsoleLogger : Logger {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger("[DocAPIpointment - App]")

    override fun info(message: String?) {
        logger.info(message)
    }

    override fun info(message: String?, vararg arguments: Any?) {
        logger.info(message, arguments)
    }

    override fun debug(message: String?) {
        logger.debug(message)
    }

    override fun debug(message: String?, vararg arguments: Any?) {
        logger.debug(message, arguments)
    }

    override fun warn(message: String?) {
        logger.warn(message)
    }

    override fun warn(message: String?, vararg arguments: Any?) {
        logger.warn(message, arguments)
    }

    override fun error(message: String?) {
        logger.error(message)
    }

    override fun error(message: String?, vararg arguments: Any?) {
        logger.error(message, arguments)
    }
}
