package com.alessiocoser

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.MINUTES

class CliApp(private val clock: Clock) {
    private val messages: MutableList<Message> = mutableListOf()

    fun send(input: Input, output: Output) {
        val command = input.read()
        if(command.contains("->")) {
            val owner = command.split("->").first().trim()
            val text = command.split("->").last().trim()
            messages.add(Message(owner, text, clock.now()))
        }

        messages.filter { it.owner == command }.asReversed().forEach { output.write(it.text + time(it.time)) }
    }

    private fun time(time: LocalDateTime): String {
        val minutesAgo = time.until(clock.now(), MINUTES)
        if(minutesAgo <= 0) {
            return ""
        }

        return " (1 minutes ago)"
    }
}
