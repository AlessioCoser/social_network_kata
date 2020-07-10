package com.alessiocoser.commandParsers

import com.alessiocoser.WallCommand

class WallCommandParser(private val command: String) : CommandParser {
    override fun canParse() = command.endsWith("wall")

    override fun parse(): WallCommand {
        val owner = command.split(" ").first().trim()
        return WallCommand(owner)
    }
}