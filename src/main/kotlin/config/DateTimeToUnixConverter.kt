package org.vikl5.config

import kotlinx.datetime.*
import org.slf4j.LoggerFactory

class DateTimeToUnixConverter {

    private val logger = LoggerFactory.getLogger("my-awesome-slack-app")
    private val timeZone = TimeZone.of("Europe/Oslo")
    private val today = Clock.System.todayIn(timeZone)

    fun unixTimeStampForPostingMessages(): Instant {
        val startPosting = today.atTime(14, 15).toInstant(timeZone)
        logger.info("Today's start posting date is: $startPosting")
        return startPosting
    }

    fun unixTimeStampForReadingMessages(): Pair<Instant, Instant> {
        val startReading = today.atTime(8, 0).toInstant(timeZone)
        logger.info("Today's start date is: $startReading")
        val endReading = today.atTime(14, 15).toInstant(timeZone)
        logger.info("Today's end date is: $endReading")

        return Pair(startReading, endReading)
    }
}