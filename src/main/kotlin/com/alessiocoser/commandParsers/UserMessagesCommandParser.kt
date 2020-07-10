package com.alessiocoser.commandParsers

import com.alessiocoser.UserMessagesCommand

class UserMessagesCommandParser(private val command: String) :
    CommandParser {
    override fun canParse() = true
    override fun parse() = UserMessagesCommand(command)
}