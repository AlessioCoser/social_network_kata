package com.alessiocoser

import java.time.LocalDateTime

interface Clock {
    fun now(): LocalDateTime
}