package com.example.missingseven.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.missingseven.Database.DAO.CountryDAO
import com.example.missingseven.Database.DAO.ItemDAO
import com.example.missingseven.Database.DAO.PlayerDAO
import com.example.missingseven.Database.DAO.TaskDao
import com.example.missingseven.Database.Entity.Country
import com.example.missingseven.Database.Entity.Item
import com.example.missingseven.Database.Entity.Player
import com.example.missingseven.Database.Entity.TaskType

/***
 * database
 */
@Database(entities = [
    TaskType.ReadingTask:: class,
    TaskType.MultipleChoiceTask::class,
    TaskType.SlidingScaleTask:: class,
    TaskType.FilterTask:: class,
    TaskType.ShortAnswerTask:: class,
    TaskType.WelcomeTask:: class,
    TaskType.LiteracyRateTask:: class,
    TaskType.GlobalLiteracyRateTask:: class,
                     Item:: class,
                     Country::class,
                     Player::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDAO
    abstract fun itemDao(): ItemDAO
    abstract fun playerDao(): PlayerDAO
    abstract fun taskDao(): TaskDao
}