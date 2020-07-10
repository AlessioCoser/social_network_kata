package com.alessiocoser

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.MINUTES

class SocialNetwork(private val clock: Clock) {
    private val messages: MutableList<Message> = mutableListOf()

    fun send(input: Input, output: Output) {
        val command = input.read()

        if (sendCommandCanApply(command)) {
            sendCommandApply(command)
        }

        userMessagesCommand(command)
            .forEach { output.write(it.text + time(it.time)) }
    }

    private fun userMessagesCommand(command: String) = messages
        .filter { it.owner == command }
        .asReversed()

    private fun sendCommandCanApply(command: String) = command.contains("->")

    private fun sendCommandApply(command: String) {
        val owner = command.split("->").first().trim()
        val text = command.split("->").last().trim()
        messages.add(Message(owner, text, clock.now()))
    }

    private fun time(time: LocalDateTime): String {
        val minutesAgo = time.until(clock.now(), MINUTES)
        if(minutesAgo <= 0) {
            return ""
        }

        return " (1 minutes ago)"
    }
}
