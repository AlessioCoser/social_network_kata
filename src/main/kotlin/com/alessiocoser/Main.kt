package com.alessiocoser

fun main() {
    val app = CliApp(RealClock())
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}