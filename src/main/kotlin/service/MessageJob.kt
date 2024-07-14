package org.vikl5.service

import com.slack.api.Slack
import com.slack.api.bolt.App
import com.slack.api.bolt.jetty.SlackAppServer
import com.slack.api.methods.SlackApiException
import com.slack.api.methods.response.chat.ChatPostMessageResponse
import kotlinx.coroutines.runBlocking
import org.quartz.CronScheduleBuilder
import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.TriggerBuilder
import org.slf4j.LoggerFactory
import org.vikl5.config.DateTimeToUnixConverter
import java.io.IOException
import java.time.*
import java.time.temporal.ChronoUnit
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit




class MessageJob: Job {
    //Channel ID: C07BJDY1693

//    private val app = App()
//    private val postDate = DateTimeToUnixConverter().startPosting
//    private val currentTime = LocalTime.now()
    private val client = Slack.getInstance()
    private val token = System.getenv("SLACK_BOT_TOKEN")
    private val channelId = "C07BJDY1693"
    private val text = "Hello world! :joy:"

    override fun execute(p0: JobExecutionContext?) {
        val logger = LoggerFactory.getLogger("my-awesome-slack-app")
        val timeToPost = DateTimeToUnixConverter().unixTimeStampForPostingMessages().epochSeconds.toInt()
        logger.info("Value of timeToPost is: $timeToPost")
        try {
            // Call the chat.postMessage method using the built-in WebClient
            val result = client.methods(token).chatScheduleMessage { r ->
                r
                    // The token you used to initialize your app
                    .channel(channelId)
                    .text(text)
                    .postAt(timeToPost)
                // You could also use a blocks[] array to send richer content
            }
            // Print result, which includes information about the message (like TS)
            logger.info("result {}", result)
        } catch (e: IOException) {
            logger.error("error: {}", e.message, e)
        } catch (e: SlackApiException) {
            logger.error("error: {}", e.message, e)
        }
    }
//    fun publishMessage() {
//        // you can get this instance via ctx.client() in a Bolt app
//        val logger = LoggerFactory.getLogger("my-awesome-slack-app")
//        try {
//            // Call the chat.postMessage method using the built-in WebClient
//            val result = client.methods(token).chatPostMessage { r ->
//                r
//                    // The token you used to initialize your app
//                    .channel(channelId)
//                    .text(text)
//                // You could also use a blocks[] array to send richer content
//            }
//            // Print result, which includes information about the message (like TS)
//            logger.info("result {}", result)
//        } catch (e: IOException) {
//            logger.error("error: {}", e.message, e)
//        } catch (e: SlackApiException) {
//            logger.error("error: {}", e.message, e)
//        }
//    }
//
//    fun schedule(){
//        val logger = LoggerFactory.getLogger("my-awesome-slack-app")
//        val timeToSchedule = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusMinutes(5)
//        try {
//            // Call the chat.postMessage method using the built-in WebClient
//            val result = client.methods(token).chatScheduleMessage { r ->
//                r
//                    // The token you used to initialize your app
//                    .channel(channelId)
//                    .text(text)
//                    .postAt(timeToSchedule.toInstant().epochSecond.toInt())
//                // You could also use a blocks[] array to send richer content
//            }
//            // Print result, which includes information about the message (like TS)
//            logger.info("result {}", result)
//        } catch (e: IOException) {
//            logger.error("error: {}", e.message, e)
//        } catch (e: SlackApiException) {
//            logger.error("error: {}", e.message, e)
//        }
//
////        val scheduler = Executors.newScheduledThreadPool(1)
////        val initialDelay = Duration.between(LocalDateTime.now(), LocalDateTime.now()
////            .withHour(14)
////            .withMinute(0)
////            .withSecond(0))
////            .toMillis()
////        scheduler.scheduleAtFixedRate({
//
////            runBlocking {
////                postMessage()
////            }
////        }, initialDelay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS)
//    }
//    private fun postMessage() {
//        val now = LocalDateTime.now()
//        val startTime = now.withHour(8).withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.UTC).toString()
//        val endTime = now.withHour(14).withMinute(0).withSecond(0).toEpochSecond(ZoneOffset.UTC).toString()
//
//
//
//
//    }
//

}

//fun schedule(){
//    app.command("/schedule") { req, ctx ->
//        val logger = ctx.logger
//        val timeToSchedule = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusMinutes(5)
//        try {
//            val result = ctx.client().chatScheduleMessage { r ->
//                r
//                    .token(token)
//                    .channel(channelId)
//                    .text(text)
//                    .postAt(timeToSchedule.toInstant().epochSecond.toInt())
//            }
//            logger.info("result {}", result)
//        } catch (e: IOException) {
//            logger.error("error: {}", e.message, e)
//        } catch (e: SlackApiException) {
//            logger.error("error: {}", e.message, e)
//            ctx.ack()
//        }
//    }