package com.alessiocoser.commandParsers

import com.alessiocoser.Command

interface CommandParser {
    fun canParse(): Boolean
    fun parse(): Command
}