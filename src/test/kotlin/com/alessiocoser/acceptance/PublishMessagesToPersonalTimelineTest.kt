package com.alessiocoser.acceptance

import com.alessiocoser.SocialNetwork
import com.alessiocoser.FakeClock
import com.alessiocoser.Input
import com.alessiocoser.SpyOutput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PublishMessagesToPersonalTimelineTest {
    @Test
    fun `happy path`() {
        val output = SpyOutput()
        val app = SocialNetwork(fakeClock())
        app.send(input("Alice -> I love the weather today"), output)
        app.send(input("Bob -> Damn! We lost!"), output)
        app.send(input("Bob -> Good game though."), output)

        app.send(input("Alice"), output)
        assertEquals(listOf("I love the weather today"), output.messages)

        output.reset()

        app.send(input("Bob"), output)
        assertEquals(listOf("Good game though.", "Damn! We lost!"), output.messages)
    }

    @Test
    fun `messages with times ago`() {
        val output = SpyOutput()
        val clock = fakeClock()
        val app = SocialNetwork(clock)

        clock.at("12:01:00")
        app.send(input("Bob -> Damn! We lost!"), output)
        clock.at("12:02:00")
        app.send(input("Bob -> Good game though."), output)

        app.send(input("Bob"), output)
        assertEquals(listOf("Good game though.", "Damn! We lost! (1 minutes ago)"), output.messages)
    }

    @Test
    fun `see all my messages on my wall`() {
        val output = SpyOutput()
        val app = SocialNetwork(fakeClock())

        app.send(input("Bob -> Damn! We lost!"), output)
        app.send(input("Bob -> Good game though."), output)

        app.send(input("Bob wall"), output)
        assertEquals(listOf("Bob - Good game though.", "Bob - Damn! We lost!"), output.messages)
    }

    @Test
    fun `see also followed messages on my wall`() {
        val output = SpyOutput()
        val app = SocialNetwork(fakeClock())

        app.send(input("Bob -> Damn! We lost!"), output)
        app.send(input("Charlie -> Good game though."), output)
        app.send(input("Bob follows Charlie"), output)

        app.send(input("Bob wall"), output)
        assertEquals(listOf("Charlie - Good game though.", "Bob - Damn! We lost!"), output.messages)
    }

    private fun fakeClock() = FakeClock("2020-01-01")

    private fun input(text: String) = object : Input {
        override fun read() = text
    }
}
