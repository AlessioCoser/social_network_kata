package com.alessiocoser

fun main() {
    val app = CliApp()
    val input = CliInput()
    val output = CliOutput()

    while (true) {
        app.send(input, output)
    }
}