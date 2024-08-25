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
            if (postMessage.message != null) {
                logger.info("The bot is posting this message: {}", postMessage.message.text)
            } else {
                logger.error("Scheduled message is null. Full response: {}", postMessage)
            }

        } catch (e: IOException) {
            logger.error("error: {}", e.message, e)
        } catch (e: SlackApiException) {
            logger.error("error: {}", e.message, e)
        }
    }

    private fun buildMessageText(userScores: List<UserScore>): String {
        val topScores = userScores.withIndex().joinToString("\n") { (index, userScore) ->
            "${index + 1}. ${userScore.username}, Score: ${userScore.score}/1000"
        }
        val highestScoreUserId = userScores.firstOrNull()?.userId?.let { "<@$it>" } ?: "No user"
        return """The highscore for today is here!!:star-struck: Thank you all for participating!:sparkles: :earth_africa: 
            |Top Scores:
            |$topScores
            |
            |:flowersmile:Congratulations to the top scorer of today: $highestScoreUserId! :tada: :tada: """.trimMargin()
    }

}