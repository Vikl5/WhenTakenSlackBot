package org.vikl5

import com.slack.api.bolt.App
import com.slack.api.bolt.jetty.SlackAppServer

fun main() {

    val app = App()

    val server = SlackAppServer(app)
    server.start()
}