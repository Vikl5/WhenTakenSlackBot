package org.vikl5.scheduler

import com.slack.api.methods.SlackApiException
import org.slf4j.LoggerFactory
import org.vikl5.config.DateTimeToUnixConverter
import org.vikl5.config.SlackConnectionInfo
import org.vikl5.util.UserScore
import java.io.IOException

class ScheduleMessage {

    private val slackConnectionInfo = SlackConnectionInfo()


    fun postOnSchedule(userScores: List<UserScore>) {
        val text = buildMessageText(userScores)
        val logger = LoggerFactory.getLogger("my-awesome-slack-app")
        val timeToPost = DateTimeToUnixConverter().unixTimeStampForPostingMessages().epochSeconds.toInt()
        logger.info("Value of timeToPost is: $timeToPost")
        try {
            val postMessage = slackConnectionInfo.client.methods(slackConnectionInfo.token).chatScheduleMessage { r ->
                r
                    .channel(slackConnectionInfo.channelId)
                    .text(text)
                    .postAt(timeToPost)
            }
            logger.info("The bot is posting this message: {}", postMessage.message.text)
        } catch (e: IOException) {
            logger.error("error: {}", e.message, e)
        } catch (e: SlackApiException) {
            logger.error("error: {}", e.message, e)
        }
    }

    private fun buildMessageText(userScores: List<UserScore>): String {
        val topScores = userScores.joinToString("\n") { userScore ->
            "User: ${userScore.username}, Score: ${userScore.score}/1000"
        }
        val highestScoreUserId = userScores.firstOrNull()?.userId?.let { "<@$it>" } ?: "No user"
        return "Top Scores:\n$topScores\n\nCongratulations to the top scorer: $highestScoreUserId!"
    }

}