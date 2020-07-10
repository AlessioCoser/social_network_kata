package com.alessiocoser

fun main() {
    val app = SocialNetwork(RealClock())
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}