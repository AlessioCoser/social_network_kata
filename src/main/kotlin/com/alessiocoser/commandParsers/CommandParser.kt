package com.alessiocoser.commandParsers

import com.alessiocoser.Command

interface CommandParser {
    fun canParse(command: String): Boolean
    fun parse(command: String): Command
}