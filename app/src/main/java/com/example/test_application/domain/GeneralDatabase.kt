package com.example.test_application.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test_application.domain.dao.TaskDao
import com.example.test_application.domain.entity.Task

@Database(entities = [Task::class], version = VERSION_DATABASE)
@TypeConverters(value = [Converters::class])
abstract class GeneralDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
}
