package com.example.test_application.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_application.domain.TABLE_TASK

@Entity(tableName = TABLE_TASK)
data class Task(
    @ColumnInfo(name = "date_start")
    val dateStart: Long,
    @ColumnInfo(name = "date_finish")
    val dateFinish: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "priority")
    val priority: Priority,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
)

enum class Priority {
    HIGH,
    NORMAL,
    LOW
}
