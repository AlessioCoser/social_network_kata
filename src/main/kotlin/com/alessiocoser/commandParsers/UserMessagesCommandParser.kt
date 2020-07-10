package com.alessiocoser.commandParsers

import com.alessiocoser.UserMessagesCommand

class UserMessagesCommandParser : CommandParser {
    override fun canParse(command: String) = true
    override fun parse(command: String) = UserMessagesCommand(command)
}