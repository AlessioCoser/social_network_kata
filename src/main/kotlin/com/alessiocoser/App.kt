package com.alessiocoser

import com.alessiocoser.commandParsers.*

class App(private val input: Input, private val output: Output, private val app: SocialNetwork) {
    fun start() {
        while (true) {
            single(input.read())
        }
    }

    fun single(text: String) {
        val command = parsers().first { it.canParse(text) }.parse(text)

        return app.send(command).forEach { output.write(it) }
    }

    private fun parsers(): List<CommandParser> {
        return listOf(
            FollowCommandParser(),
            WallCommandParser(),
            NewMessageCommandParser(),
            UserMessagesCommandParser()
        )
    }
}
