package com.alessiocoser

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.*

class TimesAgo(private val clock: Clock) {
    fun from(time: LocalDateTime): String {
        return time(time)
    }

    private fun time(time: LocalDateTime): String {
        val hoursAgo = time.until(clock.now(), HOURS)
        val minutesAgo = time.until(clock.now(), MINUTES)
        val secondsAgo = time.until(clock.now(), SECONDS)

        return when {
            hoursAgo > 0 -> "$hoursAgo ${pluralOf(hoursAgo, HOURS)} ago"
            minutesAgo > 0 -> "$minutesAgo ${pluralOf(minutesAgo, MINUTES)} ago"
            secondsAgo > 0 -> "$secondsAgo ${pluralOf(secondsAgo, SECONDS)} ago"
            else -> ""
        }
    }

    private fun pluralOf(number: Long, unit: ChronoUnit): String {
        val unitName = unit.name.toLowerCase()

        if(number == 1L) return unitName.trimEnd { it == 's' }

        return unitName
    }
}
