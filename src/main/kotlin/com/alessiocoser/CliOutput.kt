package com.alessiocoser 

class CliOutput: Output {
    override fun write(text: String) {
        println(" > $text")
    }
}