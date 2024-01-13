package com.example.test_application.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val TIME_PATTERN = "HH:mm"
private const val DATE_PATTERN = "dd.MM.yyyy"

const val MILLS_IN_DAY = 1000 * 60 * 60 * 24

inline var Calendar.year: Int
    get() = get(Calendar.YEAR)
    set(value) {
        set(Calendar.YEAR, value)
    }
inline var Calendar.month: Int
    get() = get(Calendar.MONTH)
    set(value) {
        set(Calendar.MONTH, value)
    }
inline var Calendar.day: Int
    get() = get(Calendar.DAY_OF_MONTH)
    set(value) {
        set(Calendar.DAY_OF_MONTH, value)
    }
inline var Calendar.hour: Int
    get() = get(Calendar.HOUR_OF_DAY)
    set(value) {
        set(Calendar.HOUR_OF_DAY, value)
    }
inline var Calendar.minute: Int
    get() = get(Calendar.MINUTE)
    set(value) {
        set(Calendar.MINUTE, value)
    }

fun getTimeFormatted(timestamp: Long): String = getFormatted(timestamp, TIME_PATTERN)

fun getTimeFormatted(calendar: Calendar): String = getTimeFormatted(calendar.timeInMillis)

fun getDateFormatted(timestamp: Long): String = getFormatted(timestamp, DATE_PATTERN)

fun getDateFormatted(calendar: Calendar): String = getDateFormatted(calendar.timeInMillis)

fun getHour(timestamp: Long) = Calendar.getInstance().apply { timeInMillis = timestamp }.hour

fun getHourFormatted(hour: Int) = if (hour < 10) "0$hour:00" else "$hour:00"

private fun getFormatted(timestamp: Long, pattern: String): String = try {
    SimpleDateFormat(pattern, Locale.getDefault()).format(Date(timestamp))
} catch (e: Exception) {
    e.toString()
}
