package com.example.missingseven.Database

import androidx.room.TypeConverter
import com.google.gson.Gson

/***
 * converter class used for the database
 */
class Converters {
    @TypeConverter
    fun listToJson(value: List<String>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun floatListToJson(value: List<Float>): String = Gson().toJson(value)

    @TypeConverter
    fun jsonToFloatList(value: String) = Gson().fromJson(value, Array<Float>::class.java).toList()
}