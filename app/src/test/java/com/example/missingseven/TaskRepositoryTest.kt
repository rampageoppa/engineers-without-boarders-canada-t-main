package com.example.missingseven

import com.example.missingseven.Database.DAO.TaskDao
import com.example.missingseven.Database.Entity.TaskType
import com.example.missingseven.Database.MainDatabase
import com.example.missingseven.Database.Repository.TaskRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

class TaskRepositoryTest {

    private val db: MainDatabase = mock()
    private val taskDao: TaskDao = mock()
    private lateinit var taskRepository: TaskRepository

    @Before
    fun setup(){
        whenever(db.taskDao()).thenReturn(taskDao)
        taskRepository = TaskRepository(taskDao)
    }

    @Test
    fun testGetReadingTask() = runBlocking {
        val result = mutableListOf<TaskType>()
        val task1 = TaskType.ReadingTask(
            tid = 1,
            completed = false,
            header = "task1_header",
            content = "task1_content",
            isSpecial = false
        )
        val task2 = TaskType.ReadingTask(
            tid = 2,
            completed = false,
            header = "task2_header",
            content = "task2_content",
            isSpecial = true
        )
        whenever(taskDao.getAllReadingTasks()).thenReturn(
            flow {
                emit(
                    listOf(task1, task2)
                )
            }
        )
        taskRepository.getReadingTasks {
            result.addAll(it)
        }
        assertEquals(listOf(task1, task2), result)

    }

    @Test
    fun testInsertReadingTask() = runBlocking {
        val task1 = TaskType.ReadingTask(
            tid = 1,
            completed = false,
            header = "task1_header",
            content = "task1_content",
            isSpecial = false
        )
        val task2 = TaskType.ReadingTask(
            tid = 2,
            completed = false,
            header = "task2_header",
            content = "task2_content",
            isSpecial = true
        )
        val list = listOf(task1, task2)
        var result = false
        taskRepository.insertAllReadingTasks(list) {
            result = true
        }
        assertEquals(true, result)
    }

    @Test
    fun testDeleteReadingTask() = runBlocking {
        var result = false
        taskRepository.deleteReadingTasks {
            result = true
        }
        assertEquals(true, result)
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

}