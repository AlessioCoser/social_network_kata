package com.alessiocoser

import com.alessiocoser.commandParsers.FollowCommandParser
import com.alessiocoser.commandParsers.NewMessageCommandParser
import com.alessiocoser.commandParsers.UserMessagesCommandParser
import com.alessiocoser.commandParsers.WallCommandParser
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.MINUTES

class SocialNetwork(
    private val clock: Clock,
    private val timeline: TimelineRepository,
    private val relations: RelationsRepository
) {
    fun send(input: Input, output: Output) {
        val command = input.read()

        val parsers = listOf(
            FollowCommandParser(command),
            WallCommandParser(command),
            NewMessageCommandParser(command),
            UserMessagesCommandParser(command)
        )

        return handleCommand(parsers.first { it.canParse() }.parse(), output)
    }

    private fun handleCommand(command: Command, output: Output) {
        when(command) {
            is FollowCommand -> relations.follow(command.follower, command.followed)
            is WallCommand -> wallMessages(command, output)
            is NewMessageCommand -> timeline.add(Message(command.owner, command.text, clock.now()))
            is UserMessagesCommand -> userMessages(command, output)
        }
    }

    private fun userMessages(command: UserMessagesCommand, output: Output) {
        timeline.messagesOf(listOf(command.owner))
            .forEach { output.write(it.text + time(it.time)) }
    }

    private fun wallMessages(command: WallCommand, output: Output) {
        timeline.messagesOf(relations.followedBy(command.owner) + command.owner)
            .forEach { output.write(it.owner + " - " + it.text + time(it.time)) }
    }

    private fun time(time: LocalDateTime): String {
        val minutesAgo = time.until(clock.now(), MINUTES)
        if(minutesAgo <= 0) {
            return ""
        }

        return " (1 minutes ago)"
    }
}
