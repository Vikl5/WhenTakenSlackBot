package org.vikl5.service

import com.slack.api.Slack
import com.slack.api.methods.SlackApiException
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import org.vikl5.config.DateTimeToUnixConverter
import java.io.IOException


class MessageJob: Job {
    //Channel ID: C07BJDY1693
    private val client = Slack.getInstance()
    private val token = System.getenv("SLACK_BOT_TOKEN")
    private val channelId = "C07BJDY1693"
    private val text = "Hello world! :joy:"

    override fun execute(p0: JobExecutionContext?) {
        val logger = LoggerFactory.getLogger("my-awesome-slack-app")
        val timeToPost = DateTimeToUnixConverter().unixTimeStampForPostingMessages().epochSeconds.toInt()
        logger.info("Value of timeToPost is: $timeToPost")
        try {
            val result = client.methods(token).chatScheduleMessage { r -> r
                    .channel(channelId)
                    .text(text)
                    .postAt(timeToPost)
            }
            logger.info("result {}", result)
        } catch (e: IOException) {
            logger.error("error: {}", e.message, e)
        } catch (e: SlackApiException) {
            logger.error("error: {}", e.message, e)
        }
    }

}

