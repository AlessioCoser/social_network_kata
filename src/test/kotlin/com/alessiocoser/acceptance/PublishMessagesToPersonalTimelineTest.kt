package com.alessiocoser.acceptance

import com.alessiocoser.CliApp
import com.alessiocoser.Input
import com.alessiocoser.Output
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class PublishMessagesToPersonalTimelineTest {
    @Test @Disabled
    fun `happy path`() {
        val output = SpyOutput()
        val app = CliApp()
        app.send(input("Alice -> I love the weather today"), output)
        app.send(input("Bob -> Damn! We lost!"), output)
        app.send(input("Bob -> Good game though."), output)

        app.send(input("Alice"), output)
        assertEquals(listOf("\\> I love the weather today"), output.messages)

        app.send(input("Bob"), output)
        assertEquals(listOf("\\> Good game though.", "\\> Damn! We lost!"), output.messages)
    }

    private fun input(text: String): Input {
        TODO("Not yet implemented")
    }
}

class SpyOutput: Output {
    val messages: MutableList<String> = mutableListOf()
}
