package com.alessiocoser

class InMemoryTimelineRepository {
    private val messages: MutableList<Message> = mutableListOf()

    fun add(message: Message) {
        messages.add(message)
    }

    fun messagesOf(people: List<String>): List<Message> {
        return messages
            .filter { people.contains(it.owner) }
            .asReversed()
    }
}