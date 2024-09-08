package org.vikl5.service

import com.slack.api.methods.SlackApiException
import com.slack.api.methods.response.conversations.ConversationsHistoryResponse
import com.slack.api.methods.response.conversations.ConversationsRepliesResponse
import com.slack.api.model.Message
import org.slf4j.LoggerFactory
import org.vikl5.config.DateTimeToUnixConverter
import org.vikl5.config.SlackConnectionInfo
import org.vikl5.util.UserScore
import java.io.IOException

class ChannelHistory {

    private val slackConnectionInfo = SlackConnectionInfo()
    private val logger = LoggerFactory.getLogger("my-awesome-slack-app")
    private val highScoreSorting = HighScoreSorting()
    private var userScores: List<UserScore> = emptyList()

    fun readBetweenTimeStamps() {
        val (startRead, stopRead) = DateTimeToUnixConverter().unixTimeStampForReadingMessages().let {
            it.first.epochSeconds.toString() to it.second.epochSeconds.toString()
        }
        logger.info("Value of startRead is: $startRead")
        logger.info("-----------------------------------")
        logger.info("Value of stopRead is: $stopRead")
        logger.info("-----------------------------------")

        findMessagesInChannel(stopRead, startRead)
    }

    private fun findMessagesInChannel(stopRead: String, startRead: String) {
        try {
            val readMessages = slackConnectionInfo.client.methods(slackConnectionInfo.token).conversationsHistory { r ->
                r
                    .channel(slackConnectionInfo.channelId)
                    .latest(stopRead)
                    .oldest(startRead)
            }
            val allMessages = readMessages.messages.toMutableList()
            val threadedMessages = readMessages.messages.filter { it.threadTs != null }
            threadedMessages.forEach { threadedMessage ->
                val readThreads = slackConnectionInfo.client.methods(slackConnectionInfo.token).conversationsReplies { r ->
                    r
                        .channel(slackConnectionInfo.channelId)
                        .ts(threadedMessage.ts)
                }
                allMessages.addAll(readThreads.messages)

            }
            logger.info("Messages retrieved: ${readMessages.messages.size}")

            userScores = filterMessages(allMessages)


        } catch (e: IOException) {
            logger.error("error: {}", e.message, e)
        } catch (e: SlackApiException) {
            logger.error("error: {}", e.message, e)
        }
    }

    private fun filterMessages(messages: List<Message>): List<UserScore> {
        logger.info("The filterMessages() function has been invoked")

        val filteredMessages = messages.filter { it.isValidMessage() }
        logger.info("Filtered messages count: ${filteredMessages.size}")
        val scoreRegex = """I scored (\d+)/1000""".toRegex()
        val userScoresMap = mutableMapOf<String, UserScore>()

        filteredMessages.forEach { message ->
            scoreRegex.find(message.text)?.let { matchResult ->
                val score = matchResult.groups[1]?.value?.toInt()
                if (score != null) {
                    val userId = message.user
                    val username = fetchUsername(userId)
                    val updatedUserScore = UserScore.createOrUpdate(
                        userScoresMap[userId],
                        userId,
                        username,
                        score
                    )
                    userScoresMap[userId] = updatedUserScore
                }
            }
        }
        val userScores = userScoresMap.values.toList()
        userScores.forEach { userScore ->
            logger.info("User ${userScore.username} (ID: ${userScore.userId}) highest score: ${userScore.score}")
        }

        return highScoreSorting.sortHighScore(userScores)

    }

    fun getUserScores(): List<UserScore> = userScores
    private fun Message.isValidMessage(): Boolean {
        return type == "message" && user != "U07C01384TB" && botId == null && subtype == null && !text.isNullOrEmpty()
    }

    private fun fetchUsername(userId: String): String {
        val userInfo = slackConnectionInfo.client.methods(slackConnectionInfo.token).usersInfo { r -> r.user(userId) }
        val realName = userInfo.user.profile.realName ?: "Unknown User"
        if (realName == "Unknown User") {
            logger.error("Error response: {}", userInfo)
        } else {
            logger.info("Names fetched 200 OK")
        }
        return realName
    }
}