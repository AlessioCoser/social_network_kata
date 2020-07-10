package com.alessiocoser.commandParsers

import com.alessiocoser.FollowCommand

class FollowCommandParser : CommandParser {
    override fun canParse(command: String) = command.contains("follows")

    override fun parse(command: String): FollowCommand {
        val follower = command.split("follows").first().trim()
        val followed = command.split("follows").last().trim()
        return FollowCommand(follower, followed)
    }
}