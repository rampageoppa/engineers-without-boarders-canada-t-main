package com.example.missingseven.DI

import android.content.Context
import androidx.room.Room
import com.example.missingseven.Database.DAO.CountryDAO
import com.example.missingseven.Database.DAO.ItemDAO
import com.example.missingseven.Database.DAO.PlayerDAO
import com.example.missingseven.Database.DAO.TaskDao
import com.example.missingseven.Database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Hilt Dependency Inject
 * module object for database
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideCountryDAO(database: MainDatabase): CountryDAO{
        return database.countryDao()
    }

    @Provides
    fun provideItemDAO(database: MainDatabase): ItemDAO{
        return database.itemDao()
    }

    @Provides
    fun providePlayerDAO(database: MainDatabase): PlayerDAO{
        return database.playerDao()
    }

    @Provides
    fun provideTaskDao(database: MainDatabase): TaskDao {
        return database.taskDao()
    }




    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MainDatabase {
        return Room.databaseBuilder(
            appContext,
            MainDatabase::class.java,
            "main.db"
        ).build()
    }
}