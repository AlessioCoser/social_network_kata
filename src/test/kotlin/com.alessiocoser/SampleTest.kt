package com.alessiocoser

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SampleTest {
    @Test
    fun `passing test`() {
        assertTrue(true)
    }

    @Test
    fun `failing test`() {
        assertTrue(false)
    }
}