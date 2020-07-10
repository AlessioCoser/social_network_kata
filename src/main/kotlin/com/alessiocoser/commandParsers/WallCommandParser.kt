package com.alessiocoser.commandParsers

import com.alessiocoser.WallCommand

class WallCommandParser : CommandParser {
    override fun canParse(command: String) = command.endsWith("wall")

    override fun parse(command: String): WallCommand {
        val owner = command.split(" ").first().trim()
        return WallCommand(owner)
    }
}