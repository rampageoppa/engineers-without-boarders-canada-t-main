package com.example.missingseven.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.missingseven.Database.Entity.Item
import kotlinx.coroutines.flow.Flow

/***
 * Data Access Object for the table [Item]
 */
@Dao
interface ItemDAO {
    @Query("SELECT * FROM item")
    fun getAllItems(): Flow<List<Item>>

    @Insert
    suspend fun insertAllItems(items: List<Item>)

    @Update
    suspend fun updateItem(item: Item)

    @Query("DELETE FROM item")
    suspend fun deleteAllItems()
}