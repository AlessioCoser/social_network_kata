package com.alessiocoser

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.MINUTES

class SocialNetwork(private val clock: Clock) {
    private val messages: MutableList<Message> = mutableListOf()
    private val relations: MutableMap<String, MutableList<String>> = mutableMapOf()

    fun send(input: Input, output: Output) {
        val command = input.read()

        return handleCommand(command, output)
    }

    private fun handleCommand(command: String, output: Output) {
        if(command.contains("follows")) {
            val follower = command.split("follows").first().trim()
            val followed = command.split("follows").last().trim()
            val relations = relations.getOrPut(follower) { mutableListOf() }
            relations.add(followed)
        }

        if (userWallCommandCanApply(command)) {
            return userWallCommand(command)
                .forEach { output.write(it.owner + " - " + it.text + time(it.time)) }
        }

        if (sendCommandCanApply(command)) {
            return sendCommandApply(command)
                .forEach { output.write(it.text + time(it.time)) }
        }

        return userMessagesCommand(command)
            .forEach { output.write(it.text + time(it.time)) }
    }

    private fun userWallCommand(command: String): List<Message> {
        val owner = command.split(" ").first().trim()
        println(relations)
        return messages
            .filter { it.owner == owner || followedBy(owner).contains(it.owner) }
            .asReversed()
    }

    private fun userWallCommandCanApply(command: String) = command.endsWith("wall")

    private fun userMessagesCommand(command: String) = messages
        .filter { it.owner == command }
        .asReversed()

    private fun sendCommandCanApply(command: String) = command.contains("->")

    private fun sendCommandApply(command: String): List<Message> {
        val owner = command.split("->").first().trim()
        val text = command.split("->").last().trim()
        messages.add(Message(owner, text, clock.now()))

        return emptyList()
    }

    private fun followedBy(owner: String) = relations.getOrDefault(owner, emptyList<String>())

    private fun time(time: LocalDateTime): String {
        val minutesAgo = time.until(clock.now(), MINUTES)
        if(minutesAgo <= 0) {
            return ""
        }

        return " (1 minutes ago)"
    }
}
