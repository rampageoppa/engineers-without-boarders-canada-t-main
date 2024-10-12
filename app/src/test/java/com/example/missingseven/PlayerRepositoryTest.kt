package com.example.missingseven

import com.example.missingseven.Database.DAO.PlayerDAO
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.PrefManager
import com.example.missingseven.Database.Repository.PlayerRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class PlayerRepositoryTest {

    private val playerDAO: PlayerDAO = mock()
    private val prefManager: PrefManager = mock()
    private lateinit var playerRepository: PlayerRepository
    @Before
    fun setup(){
        playerRepository = PlayerRepository(prefManager, playerDAO)
        whenever(prefManager.getInt(any())).thenReturn(9)
    }

    @Test
    fun testGetPlayer() = runBlocking {
        whenever(playerDAO.getAllPlayers()).thenReturn(
            flow {
                emit(emptyList())
            }
        )
        var result: List<Player>? = null
        playerRepository.getPlayers {
            result = it
        }
        assertEquals(emptyList<Player>(), result)
    }

    @Test
    fun testAddPlayer() = runBlocking {
        var result = false
        playerRepository.insertPlayers(emptyList()){
            result = true
        }
        assertEquals(true, result)
    }

    @Test
    fun testDeletePlayer() = runBlocking {
        var result = false
        playerRepository.deleteAllPlayers {
            result = true
        }
        assertEquals(true, result)
    }
}