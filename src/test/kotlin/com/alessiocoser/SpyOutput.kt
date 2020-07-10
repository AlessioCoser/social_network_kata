package com.alessiocoser

class SpyOutput: Output {
    val messages: MutableList<String> = mutableListOf()

    override fun write(text: String) {
        messages.add(text)
    }

    fun reset() {
        messages.removeAll(messages)
    }
}