package com.alessiocoser

class InMemoryTimelineRepository: TimelineRepository {
    private val messages: MutableList<Message> = mutableListOf()

    override fun add(message: Message) {
        messages.add(message)
    }

    override fun messagesOf(people: List<String>): List<Message> {
        return messages
            .filter { people.contains(it.owner) }
            .asReversed()
    }
}