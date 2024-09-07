package org.vikl5.config

import kotlinx.datetime.*
import org.slf4j.LoggerFactory

class DateTimeToUnixConverter {

    private val logger = LoggerFactory.getLogger("my-awesome-slack-app")
    private val timeZone = TimeZone.of(System.getenv("TIMEZONE") ?: "Europe/Oslo")
    private val today = Clock.System.todayIn(timeZone)

    companion object {
        private const val DEFAULT_POSTING_TIME = "14:35"
        private const val DEFAULT_START_READING_TIME = "7:0"
        private const val DEFAULT_END_READING_TIME = "14:30"

    }

    fun unixTimeStampForPostingMessages(): Instant {
        val postingTime = System.getenv("POSTING_TIME") ?: DEFAULT_POSTING_TIME
        val (hour, minute) = postingTime.split(":").map { it.toInt() }
        val startPosting = today.atTime(hour, minute).toInstant(timeZone)
        logger.info("Today's start posting date is: $startPosting")
        return startPosting
    }

    fun unixTimeStampForReadingMessages(): Pair<Instant, Instant> {
        val startReadingTime = System.getenv("START_READING") ?: DEFAULT_START_READING_TIME
        val endReadingTime = System.getenv("END_READING") ?: DEFAULT_END_READING_TIME

        val (startHour, startMinute) = startReadingTime.split(":").map { it.toInt() }
        val (endHour, endMinute) = endReadingTime.split(":").map { it.toInt() }

        val startReading = today.atTime(startHour, startMinute).toInstant(timeZone)
        logger.info("Today's start date is: $startReading")
        val endReading = today.atTime(endHour, endMinute).toInstant(timeZone)
        logger.info("Today's end date is: $endReading")

        return Pair(startReading, endReading)
    }
}
