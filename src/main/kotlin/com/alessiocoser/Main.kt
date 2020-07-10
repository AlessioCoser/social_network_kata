package com.alessiocoser

fun main() {
    App(
        CliInput(),
        CliOutput(),
        SocialNetwork(RealClock(), InMemoryTimelineRepository(), InMemoryRelationsRepository())
    ).start()
}