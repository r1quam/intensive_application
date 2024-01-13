package com.example.test_application.domain

import androidx.room.migration.Migration

const val DATABASE_NAME = "task_database"
const val VERSION_DATABASE = 1
const val TABLE_TASK = "task_table"

fun getMigrations(): Array<Migration> = arrayOf()
