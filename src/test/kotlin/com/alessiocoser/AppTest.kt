package com.alessiocoser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun `happy path`() {
        val output = SpyOutput()
        val app = app(fakeClock(), output)
        app.single("Alice -> I love the weather today")
        app.single("Bob -> Damn! We lost!")
        app.single("Bob -> Good game though.")

        app.single("Alice")
        assertEquals(listOf("I love the weather today"), output.messages)

        output.reset()

        app.single("Bob")
        assertEquals(listOf("Good game though.", "Damn! We lost!"), output.messages)
    }

    @Test
    fun `messages with times ago`() {
        val output = SpyOutput()
        val clock = fakeClock()
        val app = app(clock, output)

        clock.at("12:01:00")
        app.single("Bob -> Damn! We lost!")
        clock.at("12:02:00")
        app.single("Bob -> Good game though.")

        app.single("Bob")
        assertEquals(listOf("Good game though.", "Damn! We lost! (1 minutes ago)"), output.messages)
    }

    @Test
    fun `see all my messages on my wall`() {
        val output = SpyOutput()
        val app = app(fakeClock(), output)

        app.single("Bob -> Damn! We lost!")
        app.single("Bob -> Good game though.")

        app.single("Bob wall")
        assertEquals(listOf("Bob - Good game though.", "Bob - Damn! We lost!"), output.messages)
    }

    @Test
    fun `see also followed messages on my wall`() {
        val output = SpyOutput()
        val app = app(fakeClock(), output)

        app.single("Bob -> Damn! We lost!")
        app.single("Charlie -> Good game though.")
        app.single("Bob follows Charlie")

        app.single("Bob wall")
        assertEquals(listOf("Charlie - Good game though.", "Bob - Damn! We lost!"), output.messages)
    }

    private fun app(clock: FakeClock, output: SpyOutput): App {
        return App(
            stubbedInput(),
            output,
            SocialNetwork(clock, InMemoryTimelineRepository(), InMemoryRelationsRepository())
        )
    }

    private fun fakeClock() = FakeClock("2020-01-01")

    private fun stubbedInput() = object : Input {
        override fun read() = ""
    }
}
