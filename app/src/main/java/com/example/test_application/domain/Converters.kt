package com.example.test_application.domain

import androidx.room.TypeConverter
import com.example.test_application.domain.entity.Priority

class Converters {
    @TypeConverter
    fun fromPriorityToString(priority: Priority): String = priority.name

    @TypeConverter
    fun fromStringToPriority(priority: String): Priority =
        Priority.entries.find { it.name == priority } ?: Priority.LOW
}