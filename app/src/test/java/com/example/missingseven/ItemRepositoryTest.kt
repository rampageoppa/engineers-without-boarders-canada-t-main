package com.example.missingseven


import com.example.missingseven.Database.DAO.ItemDAO
import com.example.missingseven.Database.Entity.Item
import com.example.missingseven.Database.PrefManager
import com.example.missingseven.Database.Repository.ItemRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class ItemRepositoryTest {

    private val itemDAO: ItemDAO = mock()
    private val prefManager: PrefManager = mock()
    private lateinit var itemRepository: ItemRepository
    @Before
    fun setup(){
        itemRepository = ItemRepository(prefManager, itemDAO)
        whenever(prefManager.getInt(any())).thenReturn(9)
    }

    @Test
    fun testGetItem() = runBlocking {
        whenever(itemDAO.getAllItems()).thenReturn(
            flow {
                emit(emptyList())
            }
        )
        var result: List<Item>? = null
        itemRepository.getItems {
            result = it
        }
        assertEquals(emptyList<Item>(), result)
    }

    @Test
    fun testAddItem() = runBlocking {
        var result = false
        itemRepository.insertAllItems(emptyList()){
            result = true
        }
        assertEquals(true, result)
    }

    @Test
    fun testDeleteItem() = runBlocking {
        var result = false
        itemRepository.deleteAllItems {
            result = true
        }
        assertEquals(true, result)
    }
}