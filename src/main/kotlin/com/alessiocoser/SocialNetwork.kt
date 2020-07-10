package com.alessiocoser

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class SocialNetwork(
    private val clock: Clock,
    private val timeline: TimelineRepository,
    private val relations: RelationsRepository
) {
    fun send(command: Command): List<String> {
        return when(command) {
            is FollowCommand -> follow(command)
            is WallCommand -> wallMessages(command)
            is NewMessageCommand -> newMessage(command)
            is UserMessagesCommand -> userMessages(command)
        }
    }

    private fun newMessage(command: NewMessageCommand): List<String> {
        timeline.add(Message(command.owner, command.text, clock.now()))
        return emptyList()
    }

    private fun follow(command: FollowCommand): List<String> {
        relations.follow(command.follower, command.followed)
        return emptyList()
    }

    private fun userMessages(command: UserMessagesCommand): List<String> {
        return timeline.messagesOf(listOf(command.owner))
            .map { it.text + time(it.time) }
    }

    private fun wallMessages(command: WallCommand): List<String> {
        return timeline.messagesOf(relations.followedBy(command.owner) + command.owner)
            .map { it.owner + " - " + it.text + time(it.time) }
    }

    private fun time(time: LocalDateTime): String {
        val minutesAgo = time.until(clock.now(), ChronoUnit.MINUTES)
        if(minutesAgo <= 0) {
            return ""
        }

        return " (1 minutes ago)"
    }
}