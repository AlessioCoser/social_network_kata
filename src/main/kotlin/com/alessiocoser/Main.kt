package com.alessiocoser

fun main() {
    val app = SocialNetwork(RealClock(), InMemoryTimelineRepository())
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}