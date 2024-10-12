package com.example.missingseven.Database.Repository

import com.example.missingseven.Database.DAO.PlayerDAO
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.IntPair
import com.example.missingseven.Database.PrefManager
import javax.inject.Inject


/***
 * repository to call methods in [PlayerDAO]
 */
class PlayerRepository @Inject constructor(
    private val prefManager: PrefManager,
    private val playerDAO: PlayerDAO) {
    suspend fun getPlayers(callback: (List<Player>) -> Unit){
        playerDAO.getAllPlayers().collect {
            if (prefManager.getInt(IntPair.CurrTask) == 9){
                callback(it)
            }
        }
    }

    suspend fun insertPlayers(players: List<Player>, callback: () -> Unit){
        playerDAO.insertAllPlayers(players).run { callback() }
    }

    suspend fun updatePlayer(player: Player){
        playerDAO.updatePlayer(player)
    }

    suspend fun deleteAllPlayers(callback: () -> Unit){
        playerDAO.deleteAllPlayers().run {
            callback()
        }
    }
}