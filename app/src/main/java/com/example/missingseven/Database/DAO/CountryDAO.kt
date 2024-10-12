package com.example.missingseven.Database.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.missingseven.Database.Entity.Country
import kotlinx.coroutines.flow.Flow

/***
 * Data Access Object for the table [Country]
 */
@Dao
interface CountryDAO {
    @Query("SELECT * FROM country")
    fun getAllCountries(): Flow<List<Country>>

    @Insert
    suspend fun insertAllCountries(countries: List<Country>)

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}