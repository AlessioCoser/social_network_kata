package com.alessiocoser

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime.parse
import java.time.format.DateTimeFormatter.ISO_DATE_TIME

class TimesAgoTest {
    private val day = "2020-01-01"

    @Test
    fun `difference less than 1 second`() {
        val clock = FakeClock(day).at("12:00:00.000")
        val time = at("11:59:59.001")

        assertEquals("", TimesAgo(clock).from(time))
    }

    @Test
    fun `difference between 1 second and 59 seconds`() {
        val clock = FakeClock(day).at("12:00:00.000")

        assertEquals("1 second ago", TimesAgo(clock).from(at("11:59:59.000")))
        assertEquals("59 seconds ago", TimesAgo(clock).from(at("11:59:00.001")))
    }

    @Test
    fun `difference between 1 minute and 59 minutes`() {
        val clock = FakeClock(day).at("12:00:00.000")

        assertEquals("1 minute ago", TimesAgo(clock).from(at("11:59:00.000")))
        assertEquals("59 minutes ago", TimesAgo(clock).from(at("11:00:00.001")))
    }

    @Test
    fun `difference over 1 hour`() {
        val clock = FakeClock(day).at("12:00:00.000")

        assertEquals("1 hour ago", TimesAgo(clock).from(at("11:00:00.000")))
        assertEquals("12 hours ago", TimesAgo(clock).from(at("00:00:00.000")))
    }

    private fun at(time: String) = parse("${day}T$time+00:00", ISO_DATE_TIME)
}