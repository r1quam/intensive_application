package com.example.test_application.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_application.domain.dao.TaskDao
import com.example.test_application.domain.entity.Task

@Database(entities = [Task::class], version = VERSION_DATABASE)
abstract class GeneralDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
}
