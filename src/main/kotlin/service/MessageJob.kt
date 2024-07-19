package org.vikl5.service

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.vikl5.scheduler.ScheduleMessage


class MessageJob : Job {
    private val channelHistory = ChannelHistory()
    private val scheduleMessage = ScheduleMessage()
    private val logger = LoggerFactory.getLogger("my-awesome-slack-app")
    override fun execute(p0: JobExecutionContext?) {
        channelHistory.readBetweenTimeStamps()
        val userScores = channelHistory.getUserScores()
        if (userScores.isEmpty()) {
            logger.warn("No scores found to post.")
        } else {
            scheduleMessage.postOnSchedule(userScores)
        }
    }

}

