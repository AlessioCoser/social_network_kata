package com.alessiocoser.commandParsers

import com.alessiocoser.NewMessageCommand

class NewMessageCommandParser() : CommandParser {
    override fun canParse(command: String) = command.contains("->")
    override fun parse(command: String): NewMessageCommand {
        val owner = command.split("->").first().trim()
        val text = command.split("->").last().trim()
        return NewMessageCommand(owner, text)
    }
}