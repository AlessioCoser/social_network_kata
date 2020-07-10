package com.alessiocoser

import com.alessiocoser.commandParsers.FollowCommandParser
import com.alessiocoser.commandParsers.NewMessageCommandParser
import com.alessiocoser.commandParsers.UserMessagesCommandParser
import com.alessiocoser.commandParsers.WallCommandParser

fun main() {
    val app = SocialNetwork(RealClock(), InMemoryTimelineRepository(), InMemoryRelationsRepository(), listOf(
            FollowCommandParser(),
            WallCommandParser(),
            NewMessageCommandParser(),
            UserMessagesCommandParser()
        )
    )
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}