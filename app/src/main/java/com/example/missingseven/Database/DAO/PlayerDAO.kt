package com.example.missingseven.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.missingseven.Database.Entity.Player
import kotlinx.coroutines.flow.Flow

/***
 * Data Access Object for the table [Player]
 */
@Dao
interface PlayerDAO {
    @Query("SELECT * FROM player")
    fun getAllPlayers(): Flow<List<Player>>

    @Insert
    suspend fun insertAllPlayers(players: List<Player>)

    @Query("DELETE FROM player")
    suspend fun deleteAllPlayers()

    @Update
    suspend fun updatePlayer(player: Player)
}