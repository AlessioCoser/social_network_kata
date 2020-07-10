package com.alessiocoser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter.ISO_DATE_TIME

class InMemoryTimelineRepositoryTest {
    @Test
    fun `add message to Timeline`() {
        val data = parse("2020-01-01T12:00:00.000+01:00", ISO_DATE_TIME)
        val repository = InMemoryTimelineRepository()

        val message = Message("Bob", "Text", data)

        repository.add(message)

        assertEquals(listOf(message), repository.messagesOf(listOf("Bob")))
    }

    @Test
    fun `add message of 2 different people to Timeline in reverse order`() {
        val data = parse("2020-01-01T12:00:00.000+01:00", ISO_DATE_TIME)
        val repository = InMemoryTimelineRepository()

        val bobMessage = Message("Bob", "Text", data)
        val charlieMessage = Message("Charlie", "Text", data)

        repository.add(bobMessage)
        repository.add(charlieMessage)

        assertEquals(listOf(charlieMessage, bobMessage), repository.messagesOf(listOf("Bob", "Charlie")))
    }
}