package com.alessiocoser.commandParsers

import com.alessiocoser.FollowCommand

class FollowCommandParser(private val command: String) : CommandParser {
    override fun canParse() = command.contains("follows")

    override fun parse(): FollowCommand {
        val follower = command.split("follows").first().trim()
        val followed = command.split("follows").last().trim()
        return FollowCommand(follower, followed)
    }
}