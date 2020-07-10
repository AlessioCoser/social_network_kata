package com.alessiocoser

interface TimelineRepository {
    fun add(message: Message)
    fun messagesOf(people: List<String>): List<Message>
}
