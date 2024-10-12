package com.example.missingseven.Database

import android.content.SharedPreferences
import javax.inject.Inject

/***
 * wrapper class for the android [SharedPreferences]
 */
class PrefManager @Inject constructor(
    private val preferences: SharedPreferences
){
    private val editor = preferences.edit()

    fun getInt(pair: IntPair) = preferences.getInt(pair.key, pair.default)

    fun putInt(key: String, value: Int){
        editor.putInt(key, value).apply()
    }

    fun getBoolean(pair: BooleanPair) = preferences.getBoolean(pair.key, pair.default)

    fun putBoolean(key: String, value: Boolean){
        editor.putBoolean(key, value).apply()
    }

    fun getString(pair: StringPair) = preferences.getString(pair.key, pair.default)

    fun putString(key: String, value: String){
        editor.putString(key, value).apply()
    }

    companion object {
        const val CURR_TASK_ID = "FLAG_CURR_TASK_ID"
        const val DATA_INITIALIZED = "FLAG_DATA_INITIALIZED"
        const val IS_UNDER_RESETTING = "FLAG_IS_UNDER_RESETTING"
        const val SKIP = "FLAG_SKIP"
        const val EMAIL = "EMAIL"
        const val NAME = "NAME"
    }
}

sealed class IntPair(
    val key: String,
    val default: Int
) {
    object CurrTask: IntPair(PrefManager.CURR_TASK_ID, -1)
}

sealed class BooleanPair(
    val key: String,
    val default: Boolean
) {
    object DataInitialized: BooleanPair(PrefManager.DATA_INITIALIZED, false)
    object IsUnderResetting: BooleanPair(PrefManager.IS_UNDER_RESETTING, false)
    object Skip: BooleanPair(PrefManager.SKIP, false)
}

sealed class StringPair(
    val key: String,
    val default: String
) {
   object Email: StringPair(PrefManager.EMAIL, "")
   object Name: StringPair(PrefManager.NAME, "")
}