package org.vikl5

import org.vikl5.scheduler.ScheduleJob
import org.vikl5.service.MessageJob

fun main() {
    val executeJob = ScheduleJob()
    executeJob.builder()
//    val app = App()
//    val token = System.getenv("SLACK_BOT_TOKEN")
//    val channelId = "C07BJDY1693"
//    val text = "Hello world! :joy:"
//    val message = MessageJob(token, channelId, text)
//    message.schedule()
//    message.sendTestMessage()
//    message.publishMessage()

//    val server = SlackAppServer(app)
//    server.start()

}
//import com.slack.api.methods.response.chat.ChatPostMessageResponse

//fun main() {
//    val channelId = "C07BJDY1693"
//    val text = ":wave: Hi from a bot written in Kotlin!"
//
//    val slack = Slack.getInstance()
//    val token = System.getenv("SLACK_BOT_TOKEN")
//
//    val response: ChatPostMessageResponse = slack.methods(token).chatPostMessage { req ->
//        req
//            .channel(channelId)
//            .text(text)
//    }
//
//    // Optionally, you can log or print the response
////    println("Message sent successfully: $response")
//}
