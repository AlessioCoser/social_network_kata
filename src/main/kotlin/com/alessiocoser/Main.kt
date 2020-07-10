package com.alessiocoser

fun main() {
    val app = SocialNetwork(RealClock(), InMemoryTimelineRepository(), InMemoryRelationsRepository())
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}