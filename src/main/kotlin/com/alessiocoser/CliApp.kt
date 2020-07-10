package com.alessiocoser

class CliApp {
    private val messages: MutableList<Message> = mutableListOf()

    fun send(input: Input, output: Output) {
        val command = input.read()
        if(command.contains("->")) {
            val owner = command.split("->").first().trim()
            val text = command.split("->").last().trim()
            messages.add(Message(owner, text))
        }

        messages.filter { it.owner == command }.asReversed().forEach { output.write("\\> ${it.text}") }
    }
}
