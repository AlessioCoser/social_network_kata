package com.alessiocoser

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.ISO_DATE
import java.time.format.DateTimeFormatter.ISO_DATE_TIME

class FakeClock(private val day: String): Clock {
    private var time = LocalDate.parse("$day+00:00", ISO_DATE).atStartOfDay()

    override fun now(): LocalDateTime = time

    fun at(parsedTime: String): Clock {
        time = LocalDateTime.parse(day + "T" + parsedTime + "+00:00", ISO_DATE_TIME)
        return this
    }
}