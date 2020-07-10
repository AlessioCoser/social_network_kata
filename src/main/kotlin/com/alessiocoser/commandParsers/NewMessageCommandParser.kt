package com.alessiocoser.commandParsers

import com.alessiocoser.NewMessageCommand

class NewMessageCommandParser(private val command: String) : CommandParser {
    override fun canParse() = command.contains("->")
    override fun parse(): NewMessageCommand {
        val owner = command.split("->").first().trim()
        val text = command.split("->").last().trim()
        return NewMessageCommand(owner, text)
    }
}