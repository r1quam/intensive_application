package com.example.test_application.domain.repository

import com.example.test_application.domain.entity.Task

interface DomainRepository {
    suspend fun insert(task: Task)

    suspend fun getTasksByDate(timeStart: Long, timeFinish: Long): List<Task>

    suspend fun getTaskById(id: Int): Task
}
