package org.vikl5.config

import com.slack.api.Slack

data class SlackConnectionInfo(
    val client: Slack = Slack.getInstance(),
    val token: String = System.getenv("SLACK_BOT_TOKEN"),
    val channelId: String = "C07BJDY1693"
)
