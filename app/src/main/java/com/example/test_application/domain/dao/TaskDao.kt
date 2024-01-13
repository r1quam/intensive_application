package com.example.test_application.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test_application.domain.TABLE_TASK
import com.example.test_application.domain.entity.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(task: Task)

    @Query("SELECT * FROM $TABLE_TASK WHERE date_start >= :timeStart AND date_finish <= :timeFinish ORDER BY date_start")
    fun getTasks(timeStart: Long, timeFinish: Long): List<Task>

    @Query("SELECT * FROM $TABLE_TASK WHERE id = :id")
    fun getTaskById(id: Int): Task
}
