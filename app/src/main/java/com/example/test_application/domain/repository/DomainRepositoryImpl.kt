package com.example.test_application.domain.repository

import androidx.annotation.WorkerThread
import com.example.test_application.domain.dao.TaskDao
import com.example.test_application.domain.entity.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DomainRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : DomainRepository {

    @WorkerThread
    override suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    @WorkerThread
    override suspend fun getTasksByDate(timeStart: Long, timeFinish: Long): List<Task> =
        taskDao.getTasks(timeStart, timeFinish)

    override suspend fun getTaskById(id: Int): Task = taskDao.getTaskById(id)
}
