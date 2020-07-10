package com.alessiocoser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InMemoryRelationsRepositoryTest {
    @Test
    fun `empty list when a user does not follow anyone`() {
        val relations = InMemoryRelationsRepository()

        assertEquals(emptyList<String>(), relations.followedBy("Charlie"))
    }

    @Test
    fun `an user can follow multiple users`() {
        val relations = InMemoryRelationsRepository()

        relations.follow("Charlie", "Bob")
        relations.follow("Charlie", "Alice")

        assertEquals(listOf("Bob", "Alice"),relations.followedBy("Charlie"))
    }
}